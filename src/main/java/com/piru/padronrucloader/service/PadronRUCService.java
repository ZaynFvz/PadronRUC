package com.piru.padronrucloader.service;

import com.piru.padronrucloader.model.PadronRUC;
import com.piru.padronrucloader.repository.PadronRUCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import jakarta.persistence.*;

@Service
public class PadronRUCService {

    private final PadronRUCRepository padronRUCRepository;

    @Autowired
    public PadronRUCService(PadronRUCRepository padronRUCRepository, EntityManager entityManager) {
        this.padronRUCRepository = padronRUCRepository;
    }

    public List<PadronRUC> extraerDatosDesdeWeb(URL url) throws IOException {
        List<PadronRUC> padronRUCList = new ArrayList<>();

        // Descargar el archivo ZIP desde la URL
        File zipFile = descargarArchivoZip(url);

        // Extraer los datos del archivo ZIP
        padronRUCList = extraerDatosDelZip(zipFile);

        // Eliminar el archivo ZIP después de extraer los datos
        zipFile.delete();

        return padronRUCList;
    }

    private File descargarArchivoZip(URL url) throws IOException {
        long startTime = System.currentTimeMillis(); // Momento de inicio de la descarga
    
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
    
        // Crear un archivo temporal para almacenar el contenido del archivo ZIP
        File zipFile = File.createTempFile("data", ".zip");
    
        try (InputStream inputStream = connection.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(zipFile)) {
    
            // Leer y escribir el contenido del archivo ZIP en el archivo temporal
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
    
        long endTime = System.currentTimeMillis(); // Momento de finalización de la descarga
        long elapsedTime = endTime - startTime; // Tiempo transcurrido
        System.out.println("Archivo ZIP descargado en " + elapsedTime + " milisegundos");
    
        return zipFile;
    }
    

    private List<PadronRUC> extraerDatosDelZip(File zipFile) throws IOException {
        long startTime = System.currentTimeMillis(); // Momento de inicio de la extracción de datos
    
        List<PadronRUC> padronRUCList = new ArrayList<>();
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFile))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                if (!entry.isDirectory() && entry.getName().endsWith(".txt")) {
                    // Si la entrada en el archivo ZIP es un archivo de texto, extraer los datos y procesarlos
                    // Leer el archivo con la codificación UTF-8
                    try (BufferedReader reader = new BufferedReader(new InputStreamReader(zipInputStream, "UTF-8"))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            // Procesar cada línea del archivo de texto y convertirla en un objeto PadronRUC
                            long lineStartTime = System.currentTimeMillis(); // Momento de inicio de la lectura de la línea
                            PadronRUC padronRUC = procesarLineaTexto(line);
                            padronRUCList.add(padronRUC);
                            long lineEndTime = System.currentTimeMillis(); // Momento de finalización de la lectura de la línea
                            long lineElapsedTime = lineEndTime - lineStartTime; // Tiempo transcurrido en la lectura de la línea
                            System.out.println("Línea leída en " + lineElapsedTime + " milisegundos");
                        }
                    }
                }
                zipInputStream.closeEntry();
            }
        }
    
        long endTime = System.currentTimeMillis(); // Momento de finalización de la extracción de datos
        long elapsedTime = endTime - startTime; // Tiempo transcurrido en la extracción de datos
        System.out.println("Datos extraídos en " + elapsedTime + " milisegundos");
    
        return padronRUCList;
    }    
    

    public PadronRUC procesarLineaTexto(String line) {
        String[] partes = line.split("\\|");

        // Verificar si hay suficientes partes para evitar errores de índice
        if (partes.length < 15) {
            throw new IllegalArgumentException("La línea de texto no tiene suficientes campos: " + line);
        }

        PadronRUC padronRUC = new PadronRUC();
        padronRUC.setRuc(partes[0]);
        padronRUC.setNombreRazonSocial(partes[1]);
        padronRUC.setEstadoContribuyente(partes[2]);
        padronRUC.setCondicionDomicilio(partes[3]);
        padronRUC.setUbigeo(partes[4]);
        padronRUC.setTipoVia(partes[5]);
        padronRUC.setNombreVia(partes[6]);
        padronRUC.setCodigoZona(partes[7]);
        padronRUC.setTipoZona(partes[8]);
        padronRUC.setNumero(partes[9]);
        padronRUC.setInterior(partes[10]);
        padronRUC.setLote(partes[11]);
        padronRUC.setDepartamento(partes[12]);
        padronRUC.setManzana(partes[13]);
        padronRUC.setKilometro(partes[14]);

        // Inicializar la fecha y hora aquí en lugar de en el constructor
        padronRUC.setCreated(LocalDateTime.now());
        padronRUC.setCreator("-");
        padronRUC.setActive(true);

        return padronRUC;
    }
    

    public List<PadronRUC> buscarDatos(String query) {
        // Lógica para buscar datos en la base de datos basados en la consulta proporcionada
        return padronRUCRepository.findByRucContainingIgnoreCase(query);
    }

    public void cargarDatos(List<PadronRUC> padronRUCList) {
        padronRUCRepository.saveAll(padronRUCList);
    }

    @Transactional(readOnly = true)
    public List<PadronRUC> obtenerTodosLosRUC() {
        return padronRUCRepository.findAll();
    }
}

package com.piru.padronrucloader.controller;

import com.piru.padronrucloader.model.PadronRUC;
import com.piru.padronrucloader.service.PadronRUCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;
import java.net.URI;
import java.net.URISyntaxException;

@Controller
@RequestMapping("/padronruc")
public class PadronRUCController {

    private final PadronRUCService padronRUCService;

    @Autowired
    public PadronRUCController(PadronRUCService padronRUCService) {
        this.padronRUCService = padronRUCService;
    }

    @PostMapping("/cargar")
    public String cargarDatosDesdeWeb(@RequestParam("url") String fileUrl) {
        try {
            URI uri = new URI(fileUrl);
            List<PadronRUC> padronRUCList = padronRUCService.extraerDatosDesdeWeb(uri.toURL());
            padronRUCService.cargarDatos(padronRUCList);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }

        return "redirect:/"; // Redirigir al usuario a la página principal
    }

    @GetMapping("/buscar")
    public String buscarDatos(@RequestParam("query") String query, Model model) {
        List<PadronRUC> resultados = padronRUCService.buscarDatos(query);
        model.addAttribute("resultados", resultados);
        return "resultados_busqueda"; // Nombre del archivo HTML que mostrará los resultados de la búsqueda
    }
}


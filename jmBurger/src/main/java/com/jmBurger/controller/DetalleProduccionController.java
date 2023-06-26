package com.jmBurger.controller;

import com.jmBurger.entity.*;
import com.jmBurger.repository.*;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.text.SimpleDateFormat;

@Controller
public class DetalleProduccionController {
    @Autowired
    private ProduccionRepository produccionRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private DetalleProduccionRepository detalleProduccionRepository;

    @GetMapping("detalleproduccion")
    public String listarDetalleProduccion(Model model){
        model.addAttribute("detalleproduccion", detalleProduccionRepository.findAll());
        return "detalleproduccion";
    }

    @GetMapping("/generar-informe-produccion")
    public void generarInforme(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"informe-produccion.pdf\"");

        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        PDType1Font fontBold = PDType1Font.HELVETICA_BOLD;
        PDType1Font fontRegular = PDType1Font.HELVETICA;
        float fontSizeTitle = 16;
        float fontSizeText = 12;
        float margin = 50;
        float yStart = page.getMediaBox().getHeight() - margin;

        contentStream.beginText();
        contentStream.setFont(fontBold, fontSizeTitle);
        contentStream.newLineAtOffset(margin, yStart);
        contentStream.showText("Informe de detalle de producción");
        contentStream.endText();

        yStart -= fontSizeTitle + 20;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (DetalleProduccion detalle : detalleProduccionRepository.findAll()) {
            contentStream.beginText();
            contentStream.setFont(fontRegular, fontSizeText);
            contentStream.newLineAtOffset(margin, yStart);
            contentStream.showText("ID: " + detalle.getIdDetalleProduccion());
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont(fontRegular, fontSizeText);
            contentStream.newLineAtOffset(margin + 70, yStart);
            contentStream.showText("Producción: " + detalle.getProduccion().getIdProduccion());
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont(fontRegular, fontSizeText);
            contentStream.newLineAtOffset(margin + 180, yStart);
            contentStream.showText("Producto: " + detalle.getProducto().getNombreProducto());
            contentStream.endText();

            yStart -= 20;
        }

        contentStream.close();
        document.save(response.getOutputStream());
        document.close();
    }

}

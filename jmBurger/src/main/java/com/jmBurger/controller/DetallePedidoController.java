package com.jmBurger.controller;

import com.jmBurger.entity.*;
import com.jmBurger.repository.DetallePedidoRepository;
import com.jmBurger.repository.PedidoRepository;
import com.jmBurger.repository.ProductoRepository;
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
public class DetallePedidoController {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @GetMapping("detallepedidos")
    public String listarPedido(Model model){
        model.addAttribute("detallepedidos",detallePedidoRepository.findAll());
        return "detallepedidos";
    }


    @GetMapping("/generar-informe")
    public void generarInforme(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=\"informe-pedido.pdf\"");

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
        contentStream.showText("Informe de detalle de pedido");
        contentStream.endText();

        yStart -= fontSizeTitle + 20;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (DetallePedido detalle : detallePedidoRepository.findAll()) {
            contentStream.beginText();
            contentStream.setFont(fontRegular, fontSizeText);
            contentStream.newLineAtOffset(margin, yStart);
            contentStream.showText("ID: " + detalle.getIdDetallePedido());
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont(fontRegular, fontSizeText);
            contentStream.newLineAtOffset(margin + 40, yStart);
            contentStream.showText("" + detalle.getPedido().getFechaPedido());
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont(fontRegular, fontSizeText);
            contentStream.newLineAtOffset(margin + 120, yStart);
            contentStream.showText("" + detalle.getProducto().getNombreProducto());
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont(fontRegular, fontSizeText);
            contentStream.newLineAtOffset(margin + 300, yStart);
            contentStream.showText("" + detalle.getCantidad());
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont(fontRegular, fontSizeText);
            contentStream.newLineAtOffset(margin + 360, yStart);
            contentStream.showText("" + detalle.getPrecioUnitario());
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont(fontRegular, fontSizeText);
            contentStream.newLineAtOffset(margin + 420, yStart);
            contentStream.showText("" + detalle.getPrecioTotal());
            contentStream.endText();


            yStart -= 20;
        }

        contentStream.close();
        document.save(response.getOutputStream());
        document.close();
    }
}

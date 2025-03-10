package Armazenamento;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.io.RandomAccessRead;
import org.apache.pdfbox.io.RandomAccessReadBuffer;
import org.apache.pdfbox.io.RandomAccessReadBufferedFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

import org.apache.pdfbox.pdmodel.PDPage;

public class ExtraiTextoPDF {
    
    public static String extraiTextoPDF(String caminhoArquivo) {
        String texto = "Não retornou texto";
        // Extrai texto de um pdf utilizando pdfbox 
        try(PDDocument documento = Loader.loadPDF(new RandomAccessReadBufferedFile(caminhoArquivo))) {
                texto = new PDFTextStripper().getText(documento);                   
        } catch (Exception e) {
            e.printStackTrace();
        }
        return texto;
    }


    public static String extraiTextoPDFiText(String caminho) throws IOException {
        PdfReader reader = new PdfReader(caminho);
        PdfReaderContentParser parser = new PdfReaderContentParser(reader);
        String txt = "";
        TextExtractionStrategy strategy;
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
            txt = txt + strategy.getResultantText();
        }
        reader.close();
//        System.out.println(txt);
        return txt;
    }

    // Também é possível utilizar um serviço como:
    // https://docs.aws.amazon.com/textract/latest/dg/how-it-works-detecting.html
    
}

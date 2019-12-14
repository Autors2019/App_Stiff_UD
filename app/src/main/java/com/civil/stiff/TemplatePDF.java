package com.civil.stiff;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;

import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;


import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


import org.ejml.simple.SimpleMatrix;

import java.io.File;
import java.io.FileOutputStream;


import java.text.DecimalFormat;
import java.util.ArrayList;


public class TemplatePDF {
    private static final String FONT = "assets/fonts/FreeSans.ttf";
    private Context context;
    private File pdfFile;
    private Document document;
    private PdfWriter pdfWriter;
    private Paragraph  paragraph;
    private Font fTitle= new Font(Font.FontFamily.TIMES_ROMAN, 25,Font.BOLD);
    private Font fSubTitle= new Font(Font.FontFamily.TIMES_ROMAN, 20,Font.BOLD);
    private Font fText= new Font(Font.FontFamily.TIMES_ROMAN, 18,Font.BOLD);
    private Font fHighText= new Font(Font.FontFamily.TIMES_ROMAN, 18,Font.BOLD);
    private String fileName;
    private DecimalFormat sNotation= new DecimalFormat("00.###E0");

    public TemplatePDF(Context context, String fileName){
        this.context= context;
        this.fileName=fileName;
    }
    public void openDocument(){
        createFile();
        try{
            document= new Document(PageSize.A2);

            pdfWriter= PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            document.open();
        }
        catch (Exception e){
            Log.e("OpenDocument", e.toString());
        }
    }

    private void createFile(){
        File folder= new  File(Environment.getExternalStorageDirectory().toString(), "PDF");
        if(!folder.exists()) folder.mkdirs();
        Log.i("Path", folder.getPath() + " exists: " + folder.exists());
        pdfFile = new File(folder , (fileName+".pdf"));
    }

    public void closeDocument(){
        document.close();
    }

    public void addMetaData(String title, String subject, String author){
        document.addTitle(title);
        document.addSubject(subject);
        document.addAuthor(author);
    }

    public void addTitles(String title,String subTitle, String date){
        try {
            paragraph = new Paragraph();
            addChildP(new Paragraph(title, fTitle));
            addChildP(new Paragraph(subTitle, fSubTitle));
            addChildP(new Paragraph("Generado: " + date, fHighText));
            paragraph.setSpacingAfter(5);
            document.add(paragraph);
        }
        catch (Exception e){
            Log.e ("addTitles", e.toString());
        }
    }
    private void addChildP(Paragraph childParagraph){
        childParagraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(childParagraph);
    }

    public void addParagraph(String text){
        paragraph= new Paragraph(text, fText);
        paragraph.setSpacingAfter(5);
        paragraph.setSpacingBefore(5);
        try {
            document.add(paragraph);
        }
        catch (Exception e){
            Log.e ("addParagraph", e.toString());
        }
    }

    public void createTable(String[] header, ArrayList<String[]> rows, int setWidthPercentage){
        paragraph= new Paragraph();

        paragraph.setFont(fText);
        paragraph.setSpacingAfter(10);
        paragraph.setSpacingBefore(10);
        PdfPTable pdfPTable= new PdfPTable(header.length);
        pdfPTable.setWidthPercentage(setWidthPercentage);
        pdfPTable.setHorizontalAlignment(Element.ALIGN_CENTER);
        PdfPCell pdfPCell;
        int inndexC=0;
        while(inndexC<header.length){
            pdfPCell= new PdfPCell(new Phrase(header[inndexC++]));
            pdfPCell.setBackgroundColor(BaseColor.GRAY );
            pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPTable.addCell(pdfPCell);
        }

        for(int indexRow=0; indexRow< rows.size(); indexRow++){
            String[] row= rows.get(indexRow);
            for(int indexColumn=0; indexColumn< header.length; indexColumn++){
                pdfPCell= new PdfPCell( new Phrase(row[indexColumn]));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPTable.addCell(pdfPCell);
            }
        }
        paragraph.add(pdfPTable);
        try {
            document.add(paragraph);
        }
        catch (Exception e){
            Log.e ("createTable", e.toString());
        }

    }

    private Font caracteresEspeciales(){
        Font cellFont=null;
        try{
            BaseFont courier = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            cellFont= new Font(courier, 12, Font.NORMAL);
        }
        catch (Exception e){
            Log.e("caracteresEspeciales: BaseFont", e.toString());
        }
        return  cellFont;
    }


    public void createMatrix(String[] header,SimpleMatrix simpleMatrix, int setWidthPercentage) {
        paragraph = new Paragraph();


        paragraph.setFont(fText);
        paragraph.setSpacingAfter(10);
        paragraph.setSpacingBefore(10);
        PdfPTable pdfPTable = new PdfPTable(header.length);
        pdfPTable.setWidthPercentage(setWidthPercentage);
        pdfPTable.setHorizontalAlignment(Element.ALIGN_CENTER);
        PdfPCell pdfPCell;
        int inndexC=0;
        while(inndexC<header.length){
            pdfPCell= new PdfPCell(new Phrase(header[inndexC++], caracteresEspeciales()));
            pdfPCell.setBackgroundColor(BaseColor.GRAY );
            pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            pdfPTable.addCell(pdfPCell);
        }
        for(int x=0; x< simpleMatrix.numRows(); x++){
            for(int y=0;y< simpleMatrix.numCols(); y++){
                pdfPCell= new PdfPCell( new Phrase(sNotation.format(simpleMatrix.get(x,y))));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPTable.addCell(pdfPCell);
            }
        }
        paragraph.add(pdfPTable);
        try {
            document.add(paragraph);
        }
        catch (Exception e){
            Log.e ("createMatrix", e.toString());
        }
    }

    public void viewPDF(){
        Intent intent = new Intent(context, ViewPDFActivity.class);
        intent.putExtra("path", pdfFile.getAbsolutePath());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void appViewPDF(Activity activity){
        if(pdfFile.exists()){
            Uri uri= Uri.fromFile(pdfFile);
            Intent intent= new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri, "application/pdf");
            try {
                activity.startActivity(intent);
            }
            catch (ActivityNotFoundException e){
                activity.startActivity( new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id=com.adobe.reader")));
                Toast.makeText(activity.getApplicationContext(), "No cuentas con una aplicacion para visualizar PDF", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(activity.getApplicationContext(), "El archivo no se encontro", Toast.LENGTH_SHORT).show();
        }
    }


}
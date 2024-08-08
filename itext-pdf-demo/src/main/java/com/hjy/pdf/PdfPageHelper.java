package com.hjy.pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.*;

/**
 * @auther: hjy
 * @Date: 2024/3/19 16:16
 * @Description:
 */

public class PdfPageHelper extends PdfPageEventHelper {

    @Override
    public void onOpenDocument(PdfWriter writer, Document document) {
        super.onOpenDocument(writer, document);
        writer.getDirectContent().createTemplate(30, 16);
    }

    @Override
    public void onStartPage(PdfWriter writer, Document document) {
        // 获取当前页码，以便我们可以自定义每个页面的标题。

        super.onStartPage(writer, document);
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        BaseFont bf = null;
        try {
            bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        extracted(writer, document, bf);
        addWaterMark(writer, bf);
    }

    private void extracted(PdfWriter writer, Document document, BaseFont bf) {
        PdfContentByte cb = writer.getDirectContent();
        cb.saveState();
        cb.beginText();

        cb.setFontAndSize(bf, 10);
        float x = document.top(-20);
        // 左
        cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "顶部-左", document.left(), x, 0);
        // 中
        cb.showTextAligned(PdfContentByte.ALIGN_CENTER, writer.getPageNumber() + " page", (document.right() + document.left()) / 2, x, 0);
        // 右
        cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "顶部-右", document.right(), x, 0);
        // Footer
        float y = document.bottom(-20);
        // 左
        cb.showTextAligned(PdfContentByte.ALIGN_LEFT, "底部-左", document.left(), y, 0);
        // 中
        cb.showTextAligned(PdfContentByte.ALIGN_CENTER, writer.getPageNumber() + " page", (document.right() + document.left()) / 2, y, 0);
        // 右
        cb.showTextAligned(PdfContentByte.ALIGN_RIGHT, "底部-右", document.right(), y, 0);
        cb.endText();
        cb.restoreState();
    }

    /**
     * 添加水印
     * @param writer
     * @param bf
     */
    private void addWaterMark(PdfWriter writer, BaseFont bf) {
        for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 10; j++) {
                PdfContentByte cb = writer.getDirectContent();
                cb.saveState();
                cb.beginText();
                cb.setColorFill(BaseColor.GRAY);
                PdfGState gs = new PdfGState();
                gs.setFillOpacity(0.1f);
                cb.setGState(gs);
                cb.setFontAndSize(bf, 12);
                cb.showTextAligned(Element.ALIGN_MIDDLE, "添加水印", 75 * i, 80 * j, 30);
                cb.endText();
                cb.restoreState();
            }
        }
    }
}

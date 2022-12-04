package com.example.sigaamobile;

import static org.junit.Assert.assertEquals;
import android.net.Uri;
import com.example.sigaamobile.utils.AttatchDocument;
import org.junit.Test;
import java.net.URISyntaxException;

public class AttatchDocumentTest {
    private final AttatchDocument attatchDocument = new AttatchDocument();

    @Test
    public void setDocumentNameTest(){
        attatchDocument.setDocumentDirectoy("assets/aluno.json");
        assertEquals("aluno.json", attatchDocument.getDocumentName());
    }

    // expected null pointer because tests run withou android SDK
    @Test (expected = NullPointerException.class)
    public void setDocumentSizeTest(){
        attatchDocument.setDocumentDirectoy("assets/aluno.json");
        attatchDocument.setDocumentSize();
    }

    @Test
    public void getDocumentSizeTest(){
        attatchDocument.setDocumentDirectoy("assets/aluno.json");
        assertEquals(0.0, attatchDocument.getDocumentSize());
    }

    @Test
    public void setDocumentDirectoryTest() throws URISyntaxException {
        Uri uri = Uri.parse("assets/aluno.json");
        attatchDocument.setDocumentDirectoy("assets/aluno.json");
        assertEquals(uri, attatchDocument.getDocumentDirectoy());
    }
}

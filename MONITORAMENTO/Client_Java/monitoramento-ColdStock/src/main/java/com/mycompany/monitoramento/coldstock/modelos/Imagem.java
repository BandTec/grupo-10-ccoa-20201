package com.mycompany.monitoramento.coldstock.modelos;

import java.awt.Toolkit;
import java.io.File;
import javax.swing.ImageIcon;

public class Imagem {
    public ImageIcon carregarImgs(String imagemEscolhida){ 
        ImageIcon imagem = new ImageIcon(retornarCaminho(imagemEscolhida));
        //ImageIcon imagem = new ImageIcon(getClass().getClassLoader().getResource(imagemEscolhida));
        return imagem;
    }
    
    public String retornarCaminho(String imagem){
        String path = new File("Images").getAbsolutePath();
        String caminhoFinal = path + imagem; 
        return caminhoFinal;
    }
}

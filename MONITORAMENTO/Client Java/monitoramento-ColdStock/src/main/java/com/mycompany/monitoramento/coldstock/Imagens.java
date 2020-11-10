package com.mycompany.monitoramento.coldstock;

import java.io.File;
import javax.swing.ImageIcon;

public class Imagens {
    public ImageIcon carregarImgs(String imagemEscolhida){
        String path = new File("Images").getAbsolutePath();
        String caminhoFinal = path + imagemEscolhida; 
        System.out.println(caminhoFinal);
        ImageIcon imagem = new ImageIcon(caminhoFinal);
        //ImageIcon icone = new ImageIcon(getClass().getClassLoader().getResource("1601947020378.png"));
        return imagem;
    }
}

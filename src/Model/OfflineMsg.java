/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author ahmed mohsen
 */
public class OfflineMsg implements Serializable{
    private int from;
    private int to;

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }
    private String content;
    private TextColor color;
    private TextFont font;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TextColor getColor() {
        return color;
    }

    public void setColor(TextColor color) {
        this.color = color;
    }

    public TextFont getFont() {
        return font;
    }

    public void setFont(TextFont font) {
        this.font = font;
    }

    public OfflineMsg(String content, TextColor color, TextFont font,int from, int to) {
        this.content = content;
        this.color = color;
        this.font = font;
        this.from = from;
        this.to = to;
    }
}

package org.example;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Util {
    public static String displayContentOfNode(Node node) {
        if (node == null) return "";
        if (!node.hasChildNodes()) return node.getTextContent()+"\n";
        NodeList childs = node.getChildNodes();
        String res = node.getNodeName() + " : ";
        for (int i = 0; i < childs.getLength(); i++) {
            res += displayContentOfNode(childs.item(i));
        }
        return res;
    }

    public static void addSomethingToNode(String test){

    }
}

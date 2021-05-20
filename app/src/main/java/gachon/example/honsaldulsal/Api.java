package gachon.example.honsaldulsal;

import android.os.AsyncTask;
import android.util.Log;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class Api extends AsyncTask<Void,Void,String> {
    private String url;
    String ItemName, ItemPrice;

    public Api(){}
    public Api(String url){
        this.url=url;
    }
    public void setUrl(String url){
        this.url = url;
    }
    @Override
    protected String doInBackground(Void... params){
        DocumentBuilderFactory dbFactory= DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;

        try{
            dBuilder = dbFactory.newDocumentBuilder();
        }catch(ParserConfigurationException e){
            e.printStackTrace();
        }
        Document doc =null;
        try{
            doc = dBuilder.parse(url);
        }catch(IOException | SAXException e){
            e.printStackTrace();
        }

        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("item");
        for(int i=0;i<nList.getLength();i++){
            Node nNode = nList.item(i);
            if(nNode.getNodeType()==Node.ELEMENT_NODE){
                Element eElement = (Element)nNode;
                ItemName = getTagValue("goodId",eElement);
                Log.d("Api", "Item name" + ItemName);
                ItemPrice = getTagValue("goodPrice",eElement);
                Log.d("Api", "Item price" + ItemPrice);
            }
        }
        return null;
    }
    @Override
    protected void onPostExecute(String str){
        super.onPostExecute(str);
    }
    private String getTagValue(String tag, Element eElement){
        NodeList nodeList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
        Node nValue = (Node)nodeList.item(0);
        if(nValue==null)
            return null;
        return nValue.getNodeValue();
    }
}

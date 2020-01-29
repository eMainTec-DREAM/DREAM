package common.report;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import common.bean.MwareConfig;
import common.util.MessageUtil;

public class HtmlReporter
{
    private final File file;
    private final Map data;
    private final List lists;
    private final Locale locale;
    
    public static class Builder {
        private final File file;
        private Map data = new HashMap<>();
        private List lists = new ArrayList();
        private Locale locale = new Locale("ko");
        
        public Builder(File file){
            this.file = file;
        }
        public Builder data(Map data){
            this.data = data;
            return this;
        }
        public Builder addList(List list){
            this.lists.add(list);
            return this;
        }
        public Builder locale(Locale locale){
            this.locale = locale;
            return this;
        }
        
        public String build() throws Exception{
            return new HtmlReporter(this).parse();
        }
    }
    
    private HtmlReporter(Builder builder) {
        file = builder.file;
        data = builder.data;
        lists = builder.lists;
        locale = builder.locale;
    }
    
    public String parse() throws Exception {
        String contents = "";
        try
        {
            if(!file.exists()) return "NO FILE";
            Document doc = Jsoup.parse(file, "UTF-8");
            doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
            
            //dream_url
            for(Element dreamUrl:doc.select("#DREAM_URL")){
                if("a".equals(dreamUrl.tagName().toLowerCase())){
                    dreamUrl.attr("href", MwareConfig.getDreamUrl());
                }
            }
            
            //qr code
            for(Element qr:doc.select("img.QR:not(.list img)")){
                String key = qr.id();
                if(data.containsKey(key)){
                    String code = data.get(key)==null?"":data.get(key).toString();
                    String qrUrl = "https://chart.googleapis.com/chart?chs=200x200&cht=qr&chl=200x200&chld=M|0&cht=qr&chl=otpauth://totp/"+MwareConfig.getDreamUrl()+"%3Fsecret%3D"+code;
                    qr.attr("src", qrUrl);
                }
            }
            
            //image
            for(Element img:doc.select("img:not(.list img):not(.QR)")){
                String key = img.id();
                if(data.containsKey(key)){
                    String url = data.get(key)==null?"":data.get(key).toString();
                    img.attr("src", url);
                }
            }
            
            //label
            for(Element label:doc.select("label")) {
                String msg = MessageUtil.getMessage(locale, "LABEL", label.id());
                label.replaceWith(new TextNode(msg));
            }
            
            //data
            for(Element p:doc.select("p:not(.list p)")){
                String key = p.id();
                if(data.containsKey(key)){
                    String text = data.get(key)==null?"":data.get(key).toString();
                    p.replaceWith(new TextNode(text));
                }
            }
            
            //list data
            Elements tlists = doc.select(".list");
            for(int i=0; i<tlists.size(); i++){
                Element tlist = tlists.get(i);
                Element parent = tlist.parent();
                tlist.remove();
                
                if(lists.size()>i){
                    for(Object listObj:(List) lists.get(i)){
                        Element tr = tlist.clone();
                        Map map = (Map) listObj;
                        
                        //image
                        for(Element img:tr.select("img")){
                            String key = img.id();
                            if(map.containsKey(key)){
                                String url = map.get(key)==null?"":map.get(key).toString();
                                img.attr("src", url);
                            }
                        }
                        
                        //data
                        for(Element p:tr.select("p")){
                            String key = p.id();
                            if(map.containsKey(key)){
                                String text = map.get(key)==null?"":map.get(key).toString();
                                p.replaceWith(new TextNode(text));
                            }
                        }
                        
                        tr.appendTo(parent);
                    }
                }
            }
            
            contents = Parser.unescapeEntities(doc.html(), false);
        }
        catch (IOException e)
        {
            contents = "FAIL";
            e.printStackTrace();
        }
        
        return contents;
    }
}

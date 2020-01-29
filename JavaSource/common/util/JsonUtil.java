package common.util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.ArrayStack;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
 
/**
 * JSON관련 가공에 필요한 함수들
 */
public class JsonUtil {
    
    static List<Map<String, Object>> list = null;
    static ArrayStack keyStack = null;
    private static final Log log = LogFactory.getLog(JsonUtil.class);
    
    /**
     * 2차원 배열의 부모/자식 관계의 데이터를 트리형식으로 재나열 한다.
     * @param list          2차원 배열
     * @param rootId        최상위 id
     * @param idKey         유니크한 키(id가 될 필드명)
     * @param pIdKey        부모키(pId가 될 필드명)
     * @param titleKey      메뉴명이 표시될 필드명
     * @return
     * @auther ddakker 2013. 12. 12.
     */
    public static List<Map<String, Object>> convertorTreeMap(final List<Map<String, Object>> resultList, String rootId, final String idKey, final String pIdKey){
        List<Map<String, Object>> treeList2=  convertorTreeMap(resultList, rootId, idKey, pIdKey, null);

        if(list != null)
            while(list.size() > 0)
            {
                List<Map> rslt2 = new ArrayList<Map>();
                for ( int i=0; i<list.size(); i++ ) 
                {
                    boolean isM = false;
                    Map<String, Object> par = list.get(i);
                    rslt2.add(par);
                    
                    for ( int j=0; j<list.size(); j++ ) 
                    {
                        Map<String, Object> child = list.get(j);
                        if(child.get(idKey).equals(par.get(pIdKey)))isM = true;
                    }
                    
                    if(!isM && !rootId.equals(par.get(pIdKey))) keyStack.push(par.get(pIdKey));
    
                }
    
                if(keyStack.size() > 0)
                {
                    List<Map<String, Object>> rsltList2 = convertorTreeMap(rslt2, String.valueOf(keyStack.pop()), idKey, pIdKey, null);
                    treeList2.addAll(rsltList2);
                }
            }

        return treeList2;
    }
    /**
     * 2차원 배열의 부모/자식 관계의 데이터를 트리형식으로 재나열 한다.
     * @param list          2차원 배열
     * @param rootId        최상위 id
     * @param idKey         유니크한 키(id가 될 필드명)
     * @param pIdKey        부모키(pId가 될 필드명)
     * @param titleKey      메뉴명이 표시될 필드명
     * @param orderKey      정렬이 필요한경우 정령 필드명
     * @return
     * @auther 
     */
    public static List<Map<String, Object>> convertorTreeMap(List inList, String rootId, String _idKey, String _pIdKey, final String orderKey){
        List<Map<String, Object>> treeList = new ArrayList<Map<String,Object>>();   // 최종 트리

        Map<String,String> keyMap = new HashMap<String,String>();
        
//        if( inList == null || inList.size() == 0 )  throw new RuntimeException("List<Map> 데이터가 없습니다.");
        if( inList == null || inList.size() == 0 )  return treeList;
        if( inList.get(0) == null )                 throw new RuntimeException("Map 데이터가 없습니다.");
         
        list = new ArrayList<Map<String,Object>>(); // 원본데이터(Bean일경우 Map으로 변환)
        keyStack = new ArrayStack();

        //대문자 치환
        inList = CommonUtil.makeJsonNoId(inList);
        final String idKey = _idKey.toUpperCase();
        final String pIdKey = _pIdKey.toUpperCase();
        
        Iterator iter;
        for( iter=inList.iterator(); iter.hasNext(); ) {
            try{
                Object obj = iter.next();
                if( obj instanceof Map ) {
                    list.add((Map<String, Object>) obj);
                    keyMap.put(String.valueOf(((Map<String, Object>) obj).get(idKey)), String.valueOf(((Map<String, Object>) obj).get(pIdKey)));
                }else{
                    list.add((Map<String, Object>) BeanUtils.describe(obj));
                }
            }catch (Exception e) {
                throw new RuntimeException("Collection -> List<Map> 으로 변환 중 실패: " + e);
            }
        }
         
         
        int listLength = list.size();
        int loopLength = 0;
        final int[] treeLength = new int[] { 0 };

        while ( treeLength[0] != listLength && listLength != loopLength++ ) 
        {
            for ( int i=0; i<list.size(); i++ ) 
            {
                Map<String, Object> item = list.get(i);
                if ( rootId.equals(String.valueOf(item.get(pIdKey))) ) 
                {
                    Map<String, Object> view = new HashMap<String, Object>(item);
                    view.put("rows", new ArrayList<Map<String,Object>>());
                     
                    treeList.add(view);
                    list.remove(i);
                     
                    treeLength[0]++;
                          
                    if( orderKey != null ){
                        Collections.sort(treeList, new Comparator<Map<String, Object>>(){
                            public int compare(Map<String, Object> arg0, Map<String, Object> arg1) {
                                // TODO Auto-generated method stub
                                return StringUtil.valueOf(arg0.get(orderKey)).compareTo(StringUtil.valueOf(arg1.get(orderKey)));
                            }
                        });
                    }
                     
                    break;
                }
                else
                {
                    new InnerClass(){
                        public void getParentNode(List<Map<String, Object>> children, Map<String, Object> item , int j) {

                            if(children.size() > 0)
                                for ( int i=0; i<children.size(); i++ ) 
                                {
                                    Map<String, Object> child = children.get(i);
              
                                    if (child.get(child.containsKey(idKey)?idKey:idKey.toUpperCase()).equals(item.get(pIdKey)) ) {
                                        Map<String, Object> view = new HashMap<String, Object>(item);
                                        view.put("rows", new ArrayList<Map<String,Object>>());
                                        ((List<Map<String,Object>>) child.get("rows")).add(view);
                                         
                                        treeLength[0]++;
                                         
                                        list.remove(list.indexOf(item));
                                         
                                        if( orderKey != null ){
                                            Collections.sort(((List<Map<String,Object>>) child.get("rows")), new Comparator<Map<String, Object>>(){
                                                public int compare(Map<String, Object> arg0, Map<String, Object> arg1) {
                                                    // TODO Auto-generated method stub
                                                    return StringUtil.valueOf(arg0.get(orderKey)).compareTo(StringUtil.valueOf(arg1.get(orderKey)));
                                                }
                                            });
                                        }
                                        break;
                                    }else{
                                        if( ((List<Map<String,Object>>) child.get("rows")).size() > 0 ){
                                            getParentNode((List<Map<String,Object>>) child.get("rows"), item, j);
                                        }
                                    }
                                }
                        }
                    }.getParentNode(treeList, item, i);

                    //남은 건 처리 
                    if(listLength == loopLength && list.size() > 0)
                    { 
                        while ( list.size() != 0 ) 
                        {
                            for ( int k=0; k<list.size(); k++ ) 
                            {
                                Map<String, Object> lastItem = list.get(k);
    
                                if(keyMap.containsKey(String.valueOf(lastItem.get(pIdKey))))
                                {                               
                                    new InnerClass(){
                                        public void getParentNode(List<Map<String, Object>> children, Map<String, Object> item , int j) {

                                            if(children.size() > 0)
                                                for ( int i=0; i<children.size(); i++ ) 
                                                {
                                                    Map<String, Object> child = children.get(i);

                                                    if (child.get(child.containsKey(idKey)?idKey:idKey.toUpperCase()).equals(item.get(pIdKey)) ) {
                                                        Map<String, Object> view = new HashMap<String, Object>(item);
                                                        view.put("rows", new ArrayList<Map<String,Object>>());
                                                        ((List<Map<String,Object>>) child.get("rows")).add(view);
                                                        
                                                        treeLength[0]++;
                                                        
                                                        list.remove(list.indexOf(item));

                                                        if( orderKey != null ){
                                                            Collections.sort(((List<Map<String,Object>>) child.get("rows")), new Comparator<Map<String, Object>>(){
                                                                public int compare(Map<String, Object> arg0, Map<String, Object> arg1) {
                                                                    // TODO Auto-generated method stub
                                                                    return StringUtil.valueOf(arg0.get(orderKey)).compareTo(StringUtil.valueOf(arg1.get(orderKey)));
                                                                }
                                                            });
                                                        }
                                                        break;
                                                    }else{
                                                        if( ((List<Map<String,Object>>) child.get("rows")).size() > 0 ){
                                                            getParentNode((List<Map<String,Object>>) child.get("rows"), item, j);
                                                        }
                                                    }
                                                }
                                        }
                                    }.getParentNode(treeList, lastItem, k);
                                }//end if
                                else
                                {
                                    Map<String, Object> view = new HashMap<String, Object>(lastItem);
                                    view.put("rows", new ArrayList<Map<String,Object>>());
                                    
                                    treeList.add(view);
                                    list.remove(list.indexOf(lastItem));
                                }
                                
                            }//end For
                        
                        }
                        
                         
                    }
                }
            }
        }//end While

        return treeList;
    }
     
    public interface InnerClass {
        public void getParentNode(List<Map<String, Object>> list, Map<String, Object> item , int j);
    }
     
}
package common.util;

import java.awt.Color;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.struts.Globals;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonWriter;

import common.bean.ApplicationContextProvider;
import common.bean.BaseDTO;
import common.bean.ConsultConfig;
import common.bean.MwareConfig;
import common.bean.ResponseDTO;
import common.bean.User;
import common.config.service.ConfigService;
import common.exception.SqlIgnoreException;
import common.message.DataBaseMessageResources;
import common.struts.BaseAction;
import common.struts.BaseForm;
import intf.common.batch.dto.CommonBatchDTO;

/**
 * 공통으로 2군데 이상 사용하고, class를 따로 만들어 사용하지 않아도 될정도로 간단한 Util Method를 만들어 사용할때 이곳에
 * 만들어서 사용한다.
 * 
 * @author javaworker
 * @version $Id: CommonUtil.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
 * @since 1.0
 */
public class CommonUtil
{
    /**
     * main.jsp, menu.jsp 에서 사용된다. pMenuList 에서 Menu Id 가 Parent Id로 셋팅된 값을 찾아
     * 리턴한다.
     * 
     * @author javaworker
     * @since 1.0
     * @param pMenuList :
     *            전체 Menu List
     * @param pMenuId :
     *            Menu Id
     * @return subMenuHash
     */
    public static List getSubMenu(ArrayList<Map> pMenuList, String pMenuId)
    {
        List returnList = new ArrayList();
        
        for(Map map : pMenuList)
        {
            if(pMenuId.equals(String.valueOf(map.get("PMENUID"))))
            {
                returnList.add(map);
            }
        }
        
        return returnList;
        
        /*String[][] tempSubMenu = new String[0][0];

        Hashtable tempHash = null;
        String tempParentId = null;
        int tempSize = 0;
        // Menu List 는 0 번째 메인 메뉴를 제외 하고 1번째 이상 서브 메뉴가 존재하여야한다.
        if (pMenuList != null && (tempSize = pMenuList.size()) < 2)
        {
            // 0 행렬로 셋팅하고 리턴한다.
            tempSubMenu = new String[0][0];
        }
        // 0 번째는 항상 Main Menu가 있기 때문에 1번부터 검색하면 된다.
        for (int i = 1; i < tempSize; i++)
        {
            tempHash = (Hashtable) pMenuList.get(i);
            // 검색된 Sub Menu의 parent Id
            tempParentId = (String) tempHash.get("parentId");
            // 검색된 parent id 와 파라메터로 넘어온 id를 비교한다.
            if (pMenuId.equals(tempParentId))
            {
                tempSubMenu = (String[][]) tempHash.get("menu");
                break;
            }
        }
        return tempSubMenu;*/
    }

    /**
     * 리턴 경로[path]에서 pageId를 추출한다.
     * 
     * @author javaworker
     * @version $Id: CommonUtil.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
     * @since 1.0
     * @param path :
     *            page path
     * @return page id
     */
    public static String getPageIdFromPath(String path)
    {
        if (path == null || path.equals(""))
        {
            return "";
        }
        String pageId = "";
        StringTokenizer stPath = new StringTokenizer(path, ".");
        // 뒤에 확장자를 뺀 경로
        String tempPath = stPath.nextToken();
        // 확장자를 뺀 경로에서 마지막 값이 pageId 이다.
        stPath = new StringTokenizer(tempPath, "/");
        while (stPath.hasMoreTokens())
        {
            pageId = stPath.nextToken();
        }
        // 마지막 Token이 pageId 이다.
        return pageId;
    }
    
    public static String getPageIdFromREFERER(String path)
    {
        if (path == null || path.equals(""))
        {
            return "";
        }
       
        String pageId = "";
        StringTokenizer stPath = new StringTokenizer(path, "./");
        // 뒤에 확장자를 뺀 경로
        String tempPath = stPath.nextToken();
        
        while (stPath.hasMoreTokens())
        {
            if ("do".equals(tempPath))
            {
                break;
            }
            
            pageId = tempPath;
            tempPath = stPath.nextToken();          
        }

        // 마지막 Token이 pageId 이다.
        return pageId;
    }

    /**
     * HTML 본문 상단 공통 button을 생성
     * @author  javaworker
     * @version $Id: CommonUtil.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
     * @since   1.0
     * 
     * @param request
     * @param buttonId
     * @param buttonTtype
     * @return
     */
    public static String getButtonTag_TOP(HttpServletRequest request,
            String buttonId)
    {
        return CommonUtil.getButtonTag(request, buttonId, "top");
    }
    
    /**
     * HTML 본문 중간에 들어가는 button A 생성
     * @author  javaworker
     * @version $Id: CommonUtil.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
     * @since   1.0
     * 
     * @param request
     * @param buttonId
     * @param buttonTtype
     * @return
     */
    public static String getButtonTag_MIDA(HttpServletRequest request,
            String buttonId)
    {
        return CommonUtil.getButtonTag(request, buttonId, "midA");
    }
    
    /**
     * HTML 본문 하단 공통 button을 생성
     * @author  javaworker
     * @version $Id: CommonUtil.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
     * @since   1.0
     * 
     * @param request
     * @param buttonId
     * @param buttonTtype
     * @return
     */
    public static String getButtonTag_BOT(HttpServletRequest request,
            String buttonId)
    {
        return CommonUtil.getButtonTag(request, buttonId, "bot");
    }
    
    /**
     * 버튼이 해당 페이지에 있는경우 HTML태그를 생성해준다.
     * buttonId 는 T4Button 과 T4resource 테이블에 있는 Id 이어야 한다. 
     * 호출되는 스크립트 펑션은 go + buttionId(첫글자 대분자) 된다.
     * ex) buttonId : FIND
     * 호출 스크립트 펑션 : goFind(); 
     * @author  javaworker
     * @version $Id: CommonUtil.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
     * @since   1.0
     * 
     * @param request
     * @param buttonId
     * @param buttonTtype
     * @return
     */
    public static String getButtonTag(HttpServletRequest request,
            String buttonId, String buttonTtype)
    {
        //===========================================================================
        // Security 에 있는지 체크
        String [] securityObjects = (String [])request.getAttribute("SEC_BUTTON");
        
        boolean securityFlag = false;
        for (int i=0; securityObjects != null && i<securityObjects.length; i++)
        {
            String securityObjectId = securityObjects[i];
            
            // security에 등록되어 있다면  저장되지 않게 한다.
            if (buttonId.equals(securityObjectId))
            {
                securityFlag = true;
            }
        }

        // security 에 등록되어있다면 hidden input 으로 생성되게 buttonName 에 null 셋팅
        if (securityFlag) return CommonUtil.getButtonTag(buttonId, null, buttonTtype);
        
        //==========================================================================
        DataBaseMessageResources dataBaseMessageResources =
            (DataBaseMessageResources)request.getAttribute(Globals.MESSAGES_KEY);
        //==========================================================================
        // Login User Instance
        User user = (User)request.getSession().getAttribute(request.getSession().getId());
        // message key : lang.pageId.keyId   ex) ko.BUTTON.CANCEL 
        String buttonName = dataBaseMessageResources.getMessage(user.getLocale(), "BUTTON." + buttonId);
        return CommonUtil.getButtonTag(buttonId, buttonName, buttonTtype);
    }
    
    /**
     * 버튼이 해당 페이지에 있는경우 HTML태그를 생성해준다.
     * buttonId 는 T4Button 과 T4resource 테이블에 있는 Id 이어야 한다. 
     * 호출되는 스크립트 펑션은 go + buttionId(첫글자 대분자) 된다.
     * ex) buttonId : FIND
     * 호출 스크립트 펑션 : goFind(); 
     * @author  javaworker
     * @version $Id: CommonUtil.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
     * @since   1.0
     * 
     * @param buttonId
     * @param buttonName
     * @param buttonType
     * @return
     */
    public static String getButtonTag(String buttonId, String buttonName, String buttonType)
    {
        String buttonTag = "";
        
        // 버튼 이름이 없는 경우는 security 에 적용되어서 hidden으로 제어시 호출
        if (buttonName != null)
        {
            // 글자수에 따라서 클래스 를 바꿔준다.
            String buttonClass = "";
            int strLength = buttonName.getBytes().length;
            if ("search".equals(buttonType))
            {
                // midD type 버튼은 길이랑 상관없이 style적용
                buttonClass = "btn_filter";
            }
            else if ("midD".equals(buttonType))
            {
                // midD type 버튼은 길이랑 상관없이 style적용
                buttonClass = "btn_midD";
            }
            else if ("treeP".equals(buttonType))
            {
                // midD type 버튼은 길이랑 상관없이 style적용
                buttonClass = "btn_treeP";
            }
            else if (strLength <= 4)
            {
                buttonClass = "btn_" + buttonType + "_2";
            }
            else if (strLength <= 6)
            {
                buttonClass = "btn_" + buttonType + "_3";
            }
            else if (strLength <= 8)
            {
                buttonClass = "btn_" + buttonType + "_4";
            }
            else if (strLength <= 10)
            {
                buttonClass = "btn_" + buttonType + "_6";
            }
            else
            {
                buttonClass = "btn_" + buttonType + "_8";
            }
            
            // 호출 function script 생성
            String functionName = "";
            /*
             * 저장 버튼은 스크립트를 바로 호출하지 않고,
             * callGoSave 안에서 goSave 를 호출한다.
             */ 
            if ("SAVE".equals(buttonId))
            {
                functionName = "callGo" + buttonId.substring(0, 1).toUpperCase() + 
                                          buttonId.substring(1).toLowerCase() +
                                          "();";
            }
            else
            {
                // 버튼을 클릭한 경우 호출되는 스크립트 펑션
                functionName = "go" + buttonId.substring(0, 1).toUpperCase() + 
                                      buttonId.substring(1).toLowerCase() +
                                      "();";
            }
            
//            buttonTag = "<span class='"+ buttonClass +"'><span class='text'>" +
//                        "<input id='" + buttonId + "' onfocus='this.blur();' type='button" +
//                                  "' value='" + buttonName + 
//                                  "' onClick='" + functionName + 
//                                  "' onfocus='this.blur();' onmouseover=\"this.style.cursor='pointer'\" /></span></span>";
            //Search 버튼인경우 value 값을 지정하지 않는다.
            if("search".equals(buttonType))
            {
                buttonTag = "<input id='" + buttonId + "' onfocus='this.blur();' type='button" +
                        "' class='" + buttonClass + 
                        "' onClick='" + functionName + 
                        "' onfocus='this.blur();' onmouseover=\"this.style.cursor='pointer'\" />";
                
            }else
            {
                buttonTag = "<input id='" + buttonId + "' onfocus='this.blur();' type='button" +
                        "' class='" + buttonClass + 
                         "' value='" + buttonName + 
                        "' onClick='" + functionName + 
                        "' onfocus='this.blur();' onmouseover=\"this.style.cursor='pointer'\" />";
            }
            

        }
        else
        {
            buttonTag = "<input name='" + buttonId + "' type='hidden' />";
        }
        
        return buttonTag;
    }
    
    /**
     * 삭제 예정
     * @deprecated
     */
    public static String getButtonUpTag(HttpServletRequest request,
            String buttonId, String buttonTtype)
    {
        return "";
    }
    
    /**
     * 스크립트 문자열로 바꾼다.
     * " --> \\"
     * ' --> \\'
     * @author  javaworker
     * @version $Id: CommonUtil.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
     * @since   1.0
     * 
     * @param scriptStr
     * @return
     */
    public static String toScript(String scriptStr)
    {
        scriptStr = scriptStr.replaceAll("\"", "\\\\\"");
        scriptStr = scriptStr.replaceAll("\'", "\\\\\'");
        return scriptStr;
    }
    
    /**
     * 스트링중에서 특정스트링을 대소문자 구별없이 변경한다.
     * 
     * @param target  바꾸려는 문자열을 가진 원본 
     * @param old  찾을 문자열 
     * @param newer  바꿔줄 문자열 
      */

     public static String replaceStr(String target, String old, String newer) 
     {
         target = target.toUpperCase();
         old = old.toUpperCase();

         int i = target.indexOf(old);
         if (i == -1) 
         {
            return target;
         }   

         StringBuffer buf = new StringBuffer();
         buf.append(target.substring(0, i) + newer);

         if (i + old.length() < target.length()) 
         {
            buf.append(replaceStr(target.substring(i 
                    + old.length(), target.length()), old, newer));
         }
         
         return buf.toString();
         
     }

    /**
     * String 배열 과 찾는 String을 넘겨서 같은 배열 index를 리턴한다.
     * page num 계산과 상세에서 이전 다음 계산시 사용한다.
     * @author  javaworker
     * @version $Id: CommonUtil.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
     * @since   1.0
     * 
     * @param aCheckListNo
     * @param checkListNo
     * @return
     */
    public static int getSelecetedIndex(String[] aKeyListNo, String keyListNo)
    {
        int matchIndex = -1;
        int index = 0;
        
        if (aKeyListNo == null || keyListNo == null || "".equals(keyListNo))
        {
            return matchIndex;
        }
        
        for (int length=aKeyListNo.length; index<length; index++)
        {
            if (aKeyListNo[index].equals(keyListNo))
            {
                matchIndex = index;
                break;  // exit for
            }
        }
        
        return matchIndex;
    }
    
    /**
     * data에 - 가 붙여져서 있으면 - 를 빼주고,
     * 없으면 년월일로 나누어서 -를 붙여준다.
     * @author  javaworker
     * @version $Id: CommonUtil.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
     * @since   1.0
     * 
     * @param data
     * @return
     */
    public static String convertDate(String data)
    {
        String result = "";
        
        if (data == null || data.equals("") || data.length() < 7)
        {
            if(data.length() == 6)
            {
                int tempIndex = data.indexOf("-");
            
                // -1 이라면 '-'가 없는 경우이다.
                if (tempIndex == -1)
                {
                    result = data.substring(0, 4) + "-" + data.substring(4, 6);  
                }
                return result;
            }
            else
                return data;
        }
        
        int tempIndex = data.indexOf("-");
        
        // -1 이라면 '-'가 없는 경우이다.
        if (tempIndex == -1)
        {
            result = data.substring(0, 4) + "-" + data.substring(4, 6) + "-" + data.substring(6);  
        }
        else
        {
            result = data.replaceAll("-", "");
        }
        
        return result;
    }
    
    /**
     * data의 값을 무조건 '-' 붙여준다.
     * @author  javaworker
     * @version $Id: CommonUtil.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
     * @since   1.0
     * 
     * @param data
     * @return
     */
    public static String dataToDate(String data)
    {
        String result = "";
        
        if (data.length() == 8)
        {
            result = data.substring(0, 4) + "-" + data.substring(4, 6) + "-" + data.substring(6); 
        }
        else if (data.length() == 6)
        {
            result = data.substring(0, 4) + "-" + data.substring(4);  
        }
        else
        {
            result = data;
        }

        return result;
    }
    
    /**
     * data의 값을 무조건 '-' 빼준다.
     * @author  javaworker
     * @version $Id: CommonUtil.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
     * @since   1.0
     * 
     * @param data
     * @return
     */
    public static String dateToData(String data)
    {
        String result = "";
        
        // 10자리가 아니라면 리턴한다.
        if (data == null || data.length() != 10)
        {
            return data;
        }
        
        result = data.replaceAll("-", "");

        return result;
    }
    
    /**
     * 시간에 : 가 붙여져서 있으면 : 를 빼주고,
     * 없으면 시 분으로 나누어서 :를 붙여준다.
     * @author  유영근
     * @version $Id: CommonUtil.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
     * @since   1.0
     * 
     * @param data
     * @return
     */
    public static String convertTime(String data)
    {
        String result = "";
        
        //if (data == null || data.equals("") || data.length() < 4)
        if (data == null || data.equals(""))
        {
            return data;
        }
        
        int tempIndex = data.indexOf(":");
        
        // -1 이라면 ':'가 없는 경우이다.
        if (tempIndex == -1)
        {
            if(data.length() == 6)
            {
                result = data.substring(0, 2) + ":" + data.substring(2, 4) + ":" + data.substring(4, 6);
            }
            else if(data.length() == 4)
            {
                result = data.substring(0, 2) + ":" + data.substring(2, 4);
            }
            else if(data.length() < 4)
            {
                result = data.substring(0, data.length()-2) + ":" + data.substring(data.length()-2, data.length());
            }
            else
            {
                result = data.substring(0, data.length()-4) + ":" + data.substring(data.length()-4, data.length()-2) + ":" + data.substring(data.length()-2, data.length());
            }
        }
        else
        {
            result = data.replaceAll(":", "");
        }
        
        return result;
    }
    
    /**
     * 금액에 ,가 붙여져서 있으면 , 를 빼주고
     * 없으면 3자리 단위로 나우어서 ,를 붙여준다.
     * @author  oikesismiz
     * @version $Id: CommonUtil.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
     * @since   1.0
     * 
     * @param data
     * @return
     */
    public static String convertMoney(String money)
    {
        String result = "";
        
        if (money == null || money.equals(""))
        {
            return money;
        }
        
        // 마이너스 값 여부 체크 및 제거 
        boolean isMinus = false;
        if(money.startsWith("-"))
        {
            isMinus = true;
            money = money.replaceAll("-", "");
        }
        
        // 소수점이 붙은 index
        int commaIndex = money.indexOf(".");
        
        String decimalStr = "";
        if (commaIndex != -1)
        {
            decimalStr = money.substring(commaIndex+1);
            money = money.substring(0, commaIndex);
        }
        
        int tempIndex = money.indexOf(",");
        
        // -1 이라면 ','가 없는 경우이다.
        if (tempIndex == -1)
        {
            for(int i = money.length(); i > 0; i-=3)
            {
                if(i - 3 <= 0 || i == 0)
                    result = money.substring(0, i) + result;
                else
                    result = "," + money.substring(i-3, i) + result;
            }
         }
        else
        {
            result = money.replaceAll(",", "");
        }
        
        if (commaIndex != -1)
        {
            result = result+"."+decimalStr;
        }
        
        if(isMinus) result = "-" + result;
        
        return result;
    }
    
    
    
    public static String convertString(Object obj)
    {
        String result = String.valueOf(obj);
        if(result == "null") result = "";

        return result;
    }
    
    
    /**
     * parameter 셋팅시 
     * null 인경우 '' 를 리턴한다.
     * @author  javaworker
     * @version $Id: CommonUtil.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
     * @since   1.0
     * 
     * @param param
     * @return
     */
    public static String nullToBlank(String param)
    {
        if (param == null || "null".equals(param))
        {
            return "";
        }
        
        return param;
    }
    /**
     * parameter 셋팅시 
     * '' 인경우 null 를 리턴한다.
     * @author  yc.yun
     * @version $Id: CommonUtil.java,v 1.1 2013/08/30 09:09:47 yyc Exp $
     * @since   1.0
     * 
     * @param param
     * @return
     */
    public static String blankToNull(String param)
    {
        if (param.equals(""))
        {
            return null;
        }
        
        return param;
    }
    /**
     * 수자원 공사에서 사용한다.
     * ISO-8859-1 --> KSC5601 로한다.
     * @author  javaworker
     * @version $Id: CommonUtil.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
     * @since   1.0
     * 
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String convertEncoding(String orginData) 
    {
        /*
        String data = "";
        try 
        {
            data = new String(orginData.getBytes("ISO-8859-1"), "KSC5601");   
        }
        catch (Exception ex)
        {
            data = orginData;
        }
        
        return data;
        */
        return orginData;
    }
    
    /**
     * 수자원 공사에서 사용한다.
     * KSC5601 --> ISO-8859-1 로한다.
     * @author  javaworker
     * @version $Id: CommonUtil.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
     * @since   1.0
     * 
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String convertDecoding(String orginData)
    {
        /*
        String data = "";
        
        try
        {
            data = new String(orginData.getBytes("KSC5601"), "ISO-8859-1");
        }
        catch (Exception ex)
        {
            data = orginData;
        }
        
        return data;
        */
        return orginData;
    }
    
    /**
     * \ 를 \\ 로 바꾸어준다.
     * @author  javaworker
     * @version $Id: CommonUtil.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
     * @since   1.0
     * 
     * @param orginData
     * @return
     */
    public static String convertDataToWeb(String orginData)
    {
        String resultData = "";
        
        java.util.StringTokenizer stTemp = new java.util.StringTokenizer(orginData, "\\");
        
        String temp = "";
        while (stTemp.hasMoreTokens())
        {
            temp = stTemp.nextToken();
            if (stTemp.hasMoreTokens())
            {
                resultData = resultData + temp + "\\\\";
            }
            else
            {
                resultData = resultData + temp;
            }
        }
        
        return resultData;
    }
 
    /**
     * String 을 int 형으로 형변환한다.
     * 입력 인자가 null 이거나 int 형으로 변환이 안된다면 0 을 리턴한다.
     * @author  javaworker
     * @version $Id: CommonUtil.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
     * @since   1.0
     * 
     * @return
     */
    public static int parseInt(String str)
    {
        int iStr = 0;
        
        try 
        {
            iStr = Integer.parseInt(str);
        }
        catch(Exception ex){}
        
        return iStr;
    }
    
    /**
     * String 을 double 형으로 형변환한다.
     * 입력 인자가 null 이거나 double 형으로 변환이 안된다면 0 을 리턴한다.
     * @author  javaworker
     * @version $Id: CommonUtil.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
     * @since   1.0
     * 
     * @return
     */
    public static double parseDouble(String str)
    {
        double iStr = 0;
        
        try 
        {
            iStr = Double.parseDouble(str);
        }
        catch(Exception ex){}
        
        return iStr;
    }
    
    /**
     * Button 이 해당 페이지에 없는 경우 Hidden 으로 input 을 만들어서
     * 버튼 제어시 스크립트 오류가 발생하지 않게 한다.
     * @author  javaworker
     * @version $Id: CommonUtil.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
     * @since   1.0
     * 
     * @param buttonName
     * @return
     */
    public static String makeHiddenButton(String buttonName)
    {
        String buttonTag = "<script>" +
                                "document.getElementById('buttonHiddenTab').insertAdjacentHTML('BeforeEnd', '<input type=hidden name="+buttonName+" />')" +
                            "</script>";
        return buttonTag;
    }

    /**
     * 개행 문자를 \n 로 바꾸어준다.
     * @author  javaworker
     * @version $Id: CommonUtil.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
     * @since   1.0
     * 
     * @param request
     * @return
     */
    public static String converEscapeChar(String request)
    {
        if (request == null)
        {
            return "";
        }
        
        return request.replaceAll("\\n", "\\\\n");
    }

    /**
     * 이전 다음 버튼을 셋팅하는 공통 함수
     * @author  javaworker
     * @version $Id: CommonUtil.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
     * @since   1.0
     * 
     * @param noticeNo
     * @param noticeNo2
     * @return
     */
    public static String[] getPrevNextButton(String[] aKeyArray, String key)
    {
        String [] prevNextButton = {"", ""};

        int index = CommonUtil.getSelecetedIndex(aKeyArray, key);
        
        if (index == -1) return prevNextButton;
        
        if (index != 0)
        {
            prevNextButton[0] = aKeyArray[index-1];     // 이전 Key 번호
        }
        
        if (aKeyArray == null || index == aKeyArray.length-1 || aKeyArray.length == 0)
        {
            prevNextButton[1] = "";
        }
        else
        {
            prevNextButton[1] = aKeyArray[index+1];     // 다음 Key 번호
        }
        
        return prevNextButton;
    }
    
    /**
     * List 에 값을 Key 값만을 추출하여 String 배열로 리턴한다.
     * @author  javaworker
     * @version $Id: CommonUtil.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
     * @since   1.0
     * 
     * @param resultList
     * @param string
     * @return
     */
    public static String[] getKeyArray(List resultList, String keyNoName)
    {
        // return 할 String [] 에 조회된 결과의 rows 만큼 size를 잡는다.
        String [] sResults = new String[resultList.size()];
        
        Iterator rowIter = resultList.iterator();
        Map rowMap = null;

        for (int i = 0; rowIter.hasNext(); i++)
        {
            rowMap = (Map)rowIter.next(); // 해당 row의 컬럼 Map을 추출한다.
            
            sResults[i] = rowMap.get(keyNoName).toString();
        }
        
        return sResults;
    }
    
    /**
     * 첫번째 배열과 두번째 배열을 더해서 리턴한다.
     * @author  javaworker
     * @version $Id: CommonUtil.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
     * @since   1.0
     * 
     * @param firstArray
     * @param secondArray
     * @return
     */
    public static String[] addTwoStringArray(String[] firstArray, String[] secondArray)
    {
        String [] resultArray = null;
        
        try
        {
            int firstKeyLength = firstArray.length;
            int secondKeyLength = secondArray.length;
            
            resultArray = new String[firstKeyLength + secondKeyLength];

            int tempKeyIndex = 0;
            for (tempKeyIndex=0; tempKeyIndex<firstKeyLength; tempKeyIndex++)
            {
                resultArray[tempKeyIndex] = firstArray[tempKeyIndex];
            }
            
            for (int i=0; i<secondKeyLength; i++, tempKeyIndex++)
            {
                resultArray[tempKeyIndex] = secondArray[i];
            }
        }
        catch(Exception ex) 
        {
            ex.printStackTrace();
        }
        
        return resultArray;
    }
    
    /**
     * 첫번째 List에 뒤의 List를 추가해서 리턴한다.
     * @author  javaworker
     * @version $Id: CommonUtil.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
     * @since   1.0
     * 
     * @param orginList
     * @param dumpList
     * @return
     */
    public static List addListToList(List orginList, List dumpList)
    {
        if (orginList == null) return dumpList;
        if (dumpList == null) return orginList;
        
        int dumpLength = dumpList.size();
        
        for (int i=0; i<dumpLength; i++)
        {
            orginList.add(dumpList.get(i));
        }
        
        return orginList;
    }
    
    /**
     * Grid 오류나는 특수문자 표시가능한 Data 형태로 변환
     * @author  javaworker
     * @version $Id: CommonUtil.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
     * @since   1.0
     * 
     * @param orginData
     * @return
     */
    public static String replaceGridChar(String orginData)
    {
        orginData = orginData.replaceAll("\\\\", "\\\\\\\\");
        //orginData = orginData.replaceAll("&", "&amp;");
        orginData = orginData.replaceAll("\"", "&quot;");
        orginData = orginData.replaceAll("<", "&lt;");
        orginData = orginData.replaceAll(">", "&gt;");
        orginData = orginData.replaceAll("\\r", "\\\\r");
        orginData = orginData.replaceAll("\\n", "\\\\n");
        //orginData = orginData.replaceAll("\\n", "");
        return orginData;
    }
    
    /**
     * html 태그 제거 후 text만 추출
     * @author  wondo
     * @version $Id: CommonUtil.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
     * @since   1.0
     * 
     * @param orginData
     * @return
     */
    public static String getText(String content) 
    {    
        Pattern SCRIPTS = Pattern.compile("<(no)?script[^>]*>.*?</(no)?script>",Pattern.DOTALL);    
        Pattern STYLE = Pattern.compile("<style[^>]*>.*</style>",Pattern.DOTALL);    
        Pattern TAGS = Pattern.compile("<(\"[^\"]*\"|\'[^\']*\'|[^\'\">])*>");    
        //Pattern nTAGS = Pattern.compile("<\\w+\\s+[^<]*\\s*>");    
        Pattern ENTITY_REFS = Pattern.compile("&[^;]+;");    
        Pattern WHITESPACE = Pattern.compile("\\s\\s+");    
            
        Matcher m;    
            
        m = SCRIPTS.matcher(content);    
        content = m.replaceAll("");    
        m = STYLE.matcher(content);    
        content = m.replaceAll("");
        m = TAGS.matcher(content);    
        content = m.replaceAll("");    
        m = ENTITY_REFS.matcher(content);    
        content = m.replaceAll("");    
        m = WHITESPACE.matcher(content);    
        content = m.replaceAll(" ");            
            
        return content;    
    }
    
    /**
     * 문자열 특수문자를 HTML 로 만든다.
     * @author  javaworker
     * @version $Id: CommonUtil.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
     * @since   1.0
     * 
     * @param orginData
     * @return
     */
    public static String strToHtml(String orginData)
    {
        if (orginData == null) return "";
        
        orginData = orginData.replaceAll("\\\\", "\\\\\\\\");
        orginData = orginData.replaceAll("\"", "&quot;");
        orginData = orginData.replaceAll("<", "&lt;");
        orginData = orginData.replaceAll(">", "&gt;");
        orginData = orginData.replaceAll("\\r", "<p>");
        orginData = orginData.replaceAll("\\n", "<p>");

        return orginData;
    }
    
    /**
     * 문자열 /n을  <br> 로 만든다.
     * @author  sun
     * @version $Id: CommonUtil.java,v 1.1 2013/08/30 09:09:47 javaworker Exp $
     * @since   1.0
     * 
     * @param orginData
     * @return
     */
    public static String strToBr(String orginData)
    {
        if (orginData == null) return "";
            orginData = orginData.replaceAll("\\n", "<br>");

        return orginData;
    }
    
    /**
     * Make Head from TAGRID
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param resultList
     * @return
     */
    public static Map makeHeaderJson(List resultList)
    {
        List newResultList = new ArrayList();

        for(int i = 0; resultList.size() > i ; i++)
        {
            Map test = (Map)resultList.get(i);
            Iterator iter = test.keySet().iterator();
            Map headMap = new HashMap();
            
            while(iter.hasNext())
            {
                String key = (String)iter.next();
                String value = String.valueOf(test.get(key));
                
                if(key.toLowerCase().equals("id")) value = value.toUpperCase();

                headMap.put(key.toLowerCase(), value == null?"":value);
                
                headMap.remove("height");
            }
            newResultList.add(headMap); 
        }
        Map testmap = new HashMap();
        testmap.put("head", newResultList);
        return testmap;
        
    }
    
    /**
     * Make Head from result List
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param resultList
     * @param request 
     * @return
     */
    public static Map makeHeaderJsonByList(List resultList, HttpServletRequest request)
    {
      List headList = new ArrayList();
      
      if(resultList.size() > 0)
      {
          Set key = ((Map)resultList.get(0)).keySet();
          
          Iterator headIter = key.iterator();
          int listSize = key.size();
          
          //==========================================================================
          DataBaseMessageResources dataBaseMessageResources =
                  (DataBaseMessageResources)request.getAttribute(Globals.MESSAGES_KEY);
          //==========================================================================
          User user = (User)request.getSession().getAttribute(request.getSession().getId());
          
          int colCnt = 0;
          Iterator cnt = key.iterator();
          while(cnt.hasNext())
          {
              String headkey = (String)cnt.next();
              if(!headkey.startsWith("Z")) colCnt++;
          }

          while(headIter.hasNext())
          {
              Map headMap = new HashMap();
              
              String headkey = (String)headIter.next();
              headMap.put("id", headkey);
//              headMap.put("width", Math.round(1000/listSize));
              headMap.put("width", Math.round(770/colCnt));
              headMap.put("type", headkey.equals("ISDELCHECK")?"ch":"ro");
              headMap.put("align", "left");
              headMap.put("sort", "str");
              headMap.put("hidden", headkey.startsWith("Z")?"true":"false");
              
              headMap.put("value",dataBaseMessageResources.getMessage(user.getLocale(), headkey));

              headList.add(headMap);
          }
      }
      
      Map testmap = new HashMap();
      testmap.put("head", headList);
      testmap.put("data",makeJsonNoUpper(resultList));
      return testmap;
        
    }
    
    public static Map makeHeaderJsonByListForRpt(List resultList, HttpServletRequest request)
    {
      List headList = new ArrayList();
      
      if(resultList.size() > 0)
      {
          Iterator headIter = ((Map)resultList.get(0)).keySet().iterator();
          int listSize = ((Map)resultList.get(0)).keySet().size();
          
          //==========================================================================
          DataBaseMessageResources dataBaseMessageResources =
                  (DataBaseMessageResources)request.getAttribute(Globals.MESSAGES_KEY);
          //==========================================================================
          User user = (User)request.getSession().getAttribute(request.getSession().getId());
          
          while(headIter.hasNext())
          {
              Map headMap = new HashMap();
              
              String headkey = (String)headIter.next();
              headMap.put("id", headkey);
              headMap.put("width", Math.round(1000/listSize));
//              headMap.put("width", "*");
              headMap.put("type", headkey.equals("ISDELCHECK")?"ch":"ro");
              headMap.put("align", "center");
              headMap.put("sort", "str");
              headMap.put("hidden", "false");
              
//              headMap.put("value",dataBaseMessageResources.getMessage(user.getLocale(), "LABEL." + headkey));
              headMap.put("value",headkey);

              
              headList.add(headMap);
          }
      }
      
      Map testmap = new HashMap();
      testmap.put("head", headList);
      testmap.put("data",makeJson(resultList));
      return testmap;
        
    }
    
    public static Map makeHeaderJsonByListAtoCmp(List resultList)
    {
      List headList = new ArrayList();
      
      //System.out.println(resultList);
      
      if(resultList.size() > 0)
      {
          Iterator headIter = ((Map)resultList.get(0)).keySet().iterator();
          int listSize = ((Map)resultList.get(0)).keySet().size();
          
          int hdCnt = 1;
          while(headIter.hasNext())
          {
              Map headMap = new HashMap();
              
              String headkey = (String)headIter.next();
              headMap.put("id", headkey.toUpperCase());
//              headMap.put("width", Math.round(1000/listSize));
              headMap.put("width", "*");
              headMap.put("type", headkey.equals("ISDELCHECK")?"ch":"ro");
              headMap.put("align", "left");
              headMap.put("sort", "str");
              headMap.put("hidden", "ZDISPLAY".equals(headkey.toUpperCase())?"false":"true");
              
              headMap.put("value",headkey);
              
              headList.add(headMap);
              
              hdCnt++;
          }
      }
      
      Map testmap = new HashMap();
      testmap.put("head", headList);
      testmap.put("data",makeJsonNoId(resultList));
      return testmap;
        
    }
    
    /**
     * Make json Result List 
     * @author  Mark
     * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
     * @since   1.0
     * 
     * @param resultList
     * @return
     */
    public static Map makeResultJson(List resultList)
    {
        Map header = new HashMap();
        
        List newResultList = makeJson(resultList);
        
        header.put("data", newResultList);

        return header;
        
    }
    
    public static String makeJsonString(List resultList) throws IOException
    {
        Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().setPrettyPrinting().setFieldNamingStrategy(new UnderScoreToUpper()).create();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream oos = new DataOutputStream(bos);

        JsonWriter writer = new JsonWriter(new OutputStreamWriter(oos,"UTF8"));
//        writer.setIndent("  ");
        
        String jsonString;
        try{
            
            writer.beginArray();
            gson.toJson(resultList, List.class, writer);
            writer.endArray();
            writer.close();
        
            jsonString  = bos.toString("UTF-8");
        }
        finally{
            
            writer.close();
            bos.close();
            oos.close();
        }

        jsonString = jsonString.substring(jsonString.indexOf("[")+1 , jsonString.length()-1);
        jsonString = jsonString.replaceAll("\"ID\"", "id");
        jsonString = jsonString.replaceAll("\"OPEN\"", "open");
        jsonString = jsonString.replaceAll("\"false\"", "false");
        jsonString = jsonString.replaceAll("\"true\"", "true");
        jsonString = jsonString.replaceAll("\"ROWS\"", "rows");
        jsonString = jsonString.replaceAll("\"id\"", "id");
        jsonString = jsonString.replaceAll("\\\\", "\\\\\\\\");
        
        return jsonString;
    }
    
    public static String makeJsonString(Map resultMap) throws IOException
    {
        Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().setFieldNamingStrategy(new UnderScoreToUpper()).create();

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream oos = new DataOutputStream(bos);

        JsonWriter writer = new JsonWriter(new OutputStreamWriter(oos,"UTF8"));
        String jsonString;
//        writer.setIndent("  ");
        try{
        writer.beginArray();
        gson.toJson(resultMap, Map.class, writer);
        writer.endArray();
        writer.flush();
        writer.close();
        
        jsonString  = bos.toString("UTF-8");
        }
        finally{
            
            writer.close();
            bos.close();
            oos.close();
        }
        
        jsonString = jsonString.substring(jsonString.indexOf("[")+1 , jsonString.length()-1);
//        jsonString = jsonString.replaceAll("\"ID\"", "id");
        
        return jsonString;
    }
    
    private static class UnderScoreToUpper implements FieldNamingStrategy{

        @Override
        public String translateName(Field f) {

            String name = f.getName();

            Pattern p = Pattern.compile("[_][a-z]{1}");

            Matcher m = p.matcher(name);

            while(m.find()){
                String c = m.group(0).replace("_", "").toUpperCase();
                name = name.replace(m.group(0), c);

            }
            return name;
        }

    }
    
    public static List makeNullToBlank(List resultList)
    {
        return makeJson(resultList);
    }
    
    public static List makeJson(List resultList)
    {
        List newResultList = new ArrayList();
        LinkedHashMap newMap = new LinkedHashMap();
        
        for(int i = 0; resultList.size() > i ; i++)
        {
            Map test = new HashMap();
            if(resultList.get(i) instanceof BaseDTO) test = new Gson().fromJson(new Gson().toJson(resultList.get(i)), Map.class);
            else if(resultList.get(i) instanceof Map) test = (Map)resultList.get(i);
            newMap.clear();
            
            Iterator iter = test.keySet().iterator();
            String id = UUID.randomUUID().toString().replace("-", "");
            newMap.put("ID", id);
            
            while(iter.hasNext())
            {
                String key = (String)iter.next();
                newMap.put(key.toUpperCase(), test.get(key) == null?"":test.get(key));
            }
            newResultList.add(newMap.clone()); 
        }

        return newResultList;
        
    }
    
    public static List makeJsonNoId(List resultList)
    {
        List newResultList = new ArrayList();
        LinkedHashMap newMap = new LinkedHashMap();
        
        for(int i = 0; resultList.size() > i ; i++)
        {
            Map test = (Map)resultList.get(i);
            newMap.clear();
            
            Iterator iter = test.keySet().iterator();
            
            while(iter.hasNext())
            {
                String key = (String)iter.next();
                newMap.put(key.toUpperCase(), test.get(key) == null?"":test.get(key));
            }
            newResultList.add(newMap.clone()); 
        }

        return newResultList;
        
    }
    
    public static List makeJsonNoUpper(List resultList)
    {
        List newResultList = new ArrayList();

        for(int i = 0; resultList.size() > i ; i++)
        {
            Map test = (Map)resultList.get(i);
//            LinkedHashMap newTest = new LinkedHashMap();
//            newTest.putAll(test);
            
            LinkedHashMap newMap = new LinkedHashMap();
            
            Iterator iter = test.keySet().iterator();
            
            while(iter.hasNext())
            {
                String key = (String)iter.next();
                newMap.put(key, test.get(key) == null?"":test.get(key));
            }
            newResultList.add(newMap); 
        }

        return newResultList;
        
    }
    
    public static List makeTreeResultJson(List resultList)
    {
        return makeJsonNoId(resultList);
    }
    
    public static List makeTreeExcelList(List list)
    {

        List resultList = new ArrayList();
        List clonedList = new ArrayList();
        List subList = null;
        String locId;
        String pLocId;
        String minLvl;
        String curLvl;
        
        clonedList.addAll(list);
        
        if(list != null)
        for(Object resultMap : list)
        {
            Map resultM = (Map)resultMap;

            resultList.add(resultM);
            
            resultList.addAll(makeSubList(resultM));
        }

        return resultList;
 
    }
    
    public static List makeSubList(Map resultMap)
    {
        List rtnList = new ArrayList();
        List subList2 = new ArrayList();
        List subList3 = new ArrayList();
        if(resultMap.containsKey("rows"))
        {
            subList3 = (List)resultMap.get("rows");
            
            for(Object resultObj2 : subList3)
            {
                Map resultMap2 = (Map)resultObj2;
                rtnList.add(resultMap2);
                
                if(resultMap2.containsKey("rows"))
                {
                    subList2 = makeSubList(resultMap2);
                    
                    if(subList2.size() > 0) rtnList.addAll(subList2);
                }
            }

        }
        return rtnList;
    }
    
    public static List makeTreeList(List list, String pCol, String col, boolean isExcel)
    {
//        if(!isExcel)
//        {
            List resultList = new ArrayList();
            List clonedList = new ArrayList();
            List subList = null;
            String locId;
            String pLocId;
            String minLvl;
            String curLvl;
            
            clonedList = makeJsonNoId(list);

            if(list != null)
            for(Object resultMap : list)
            {
                Map resultM = (Map)resultMap;
                
                locId = String.valueOf(resultM.get(col));
                pLocId = String.valueOf(resultM.get(pCol));
                minLvl = String.valueOf(resultM.get("MINLVL"));
                curLvl = String.valueOf(resultM.get("LVL"));
                
                if(curLvl.equals(minLvl))
                {
                    clonedList.remove(resultM);
                    
                    subList = getSubList(locId, clonedList, pCol, col);
                    if(subList.size() > 0) resultM.put("rows", subList);
      
                    resultList.add(resultM);
                }
                //else clonedList.remove(resultM);
                
            }
        
            return resultList;
//        }
//        else
//            return list;
    }
    
    public static List getSubList(String pCode, List resultList, String pCodeCol, String codeCol)
    {
        List subList = new ArrayList();
        List clonedList = new ArrayList();
        Map rMap = null;
        
        clonedList.addAll(resultList);
        
        //Parent Equip Location Code 가 0인 Equipment를 찾아주세요.
        for(Object object : resultList)
        {
            rMap = (Map)object;
            if(String.valueOf(rMap.get(pCodeCol)).equals(pCode))
            {
                String eqLocId = String.valueOf(rMap.get(codeCol));
                clonedList.remove(rMap);
                List list = getSubList(eqLocId, clonedList, pCodeCol, codeCol);
                
                rMap.put("rows", list);
                subList.add(rMap);
            }
        }
        
        return subList;
    }
    
    /** 
     * Tree 형식의 Data를 List로 돌림 
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param resultList
     * @return
     */
    public static List resSubList(Map resultMap)
    {
        List clonedList = new ArrayList();
        Map rMap = null;

            if(rMap.containsKey("rows"))
            {
                List rstList =  (List)rMap.get("rows");
                List rstList2 = null;
                Map omap = null;
                for(Object obj : rstList )
                {
                    omap = (Map)obj;
                    clonedList.add(omap);
                    if(omap.containsKey("rows")) rstList2 = resSubList(omap);
                    
                    clonedList.add(rstList2);
                }
            }
   
        return clonedList;
    }
    
    public static String[] makeRepRemoval(String[] arr){
        ArrayList<String> tempArr = new ArrayList<String>();
        for (int i = 0; i < arr.length; i++) {
            boolean isHad = false;
            if(tempArr.size()==0){
                tempArr.add(arr[i]);
            }else{
                for (int j = 0; j < tempArr.size(); j++) {
                    if(arr[i].equals(tempArr.get(j))){
                        isHad = true;
                    }
                }
                if(!isHad)
                    tempArr.add(arr[i]);
            }
        }
        
        String[] reArr = new String[tempArr.size()];
        for (int i = 0; i < tempArr.size(); i++) {
            reArr[i] = tempArr.get(i);
        }
        return reArr;
    }
    
    public static Object makeDTO(Map<String,Object> mapDto, Class className) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException
    {
        Method configMethod = null;
        String methodName = "";

        Object obj = className.newInstance();
//        Method[] methods = className.getMethods();
        boolean isMethod = false;
        
        List<Method> methods = new ArrayList<Method>();
        methods = getAllMethods(methods, className);
        
        for( String key : mapDto.keySet() )
        {
            methodName = getMethodName(key).toUpperCase();

            isMethod = false;
            for(Method method : methods)
            {
                if(methodName.equals(method.getName().toUpperCase()))
                { 
                    isMethod = true;
                    methodName = method.getName();
                }
                
                if(isMethod) break;
            }
            
            if(!isMethod) continue;
            
            configMethod = className.getMethod(methodName, new Class[]{java.lang.String.class});
            configMethod.invoke(obj, new Object[]{StringUtil.valueOf(mapDto.get(key))});

        }

        return obj;
    }
    
    public static Object makeDTO(Map<String,Object> mapDto, T elem) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException
    {
        Method configMethod = null;
        String methodName = "";

        Class className = elem.getClass();
        
        Object obj = elem;
//        Method[] methods = className.getMethods();
        
        List<Method> methods = new ArrayList<Method>();
        methods = getAllMethods(methods, className);
        
        boolean isMethod = false;
        
        for( String key : mapDto.keySet() )
        {
            methodName = getMethodName(key).toUpperCase();
            
            isMethod = false;
            for(Method method : methods)
            {
                if(methodName.equals(method.getName().toUpperCase()))
                { 
                    isMethod = true;
                    methodName = method.getName();
                }
                
                if(isMethod) break;
            }
            
            if(!isMethod) continue;
            
            configMethod = className.getMethod(methodName, new Class[]{java.lang.String.class});
            configMethod.invoke(obj, new Object[]{new String(String.valueOf(mapDto.get(key)))});

        }

        return obj;
    }
    
    public static List<Field> getAllFields(List<Field> fields, Class<?> type)
    {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));

        if (type.getSuperclass() != null) {
            getAllFields(fields, type.getSuperclass());
        }

        return fields;
    }
    
    public static List<Method> getAllMethods(List<Method> fields, Class<?> type)
    {
        fields.addAll(Arrays.asList(type.getDeclaredMethods()));

        if (type.getSuperclass() != null) {
            getAllMethods(fields, type.getSuperclass());
        }

        return fields;
    }
    
    private static String getMethodName(String configName) 
    {
        return getMethodName(configName, "set"); 
    }
    
    private static String getMethodName(String configName, String mType) 
    {
        mType = mType.toLowerCase();
        StringTokenizer stConfigName = new StringTokenizer(configName, "_");
        
        String methodName = "";
        while (stConfigName.hasMoreTokens())
        {
            String nameToken = stConfigName.nextToken();
            
            methodName = methodName + nameToken.substring(0, 1).toUpperCase() + nameToken.substring(1).toLowerCase();
        }
               
        return mType + methodName;
    }
    
    // 엑셀파일 생성
    public static void makeExlFile(List headList, List data, String filePath, String fileName, String isProtected, String isTree) throws IOException
    {
        SXSSFWorkbook wb = new SXSSFWorkbook(100);
        
        SXSSFSheet sheet = wb.createSheet("sheet");
        
        wb = makeSheet(sheet, headList, data, isProtected, isTree);
        
        // 만약 taconfig에 지정해놓은 FILE_DIR 폴더가 없으면 생성
        File dir = new File(filePath);
        if(!dir.exists())
        {
           dir.mkdirs();
        }
        
        //File file = new File(filePath + "\\" + fileName); // Linux Server에서 에러 발생 
        File file = new File(filePath +  fileName);
        FileOutputStream fOut = new FileOutputStream(file);
        
        try
        {
            wb.write(fOut);
            fOut.close();
            wb.dispose();
        }
        catch (Exception e) {
            fOut.close();
            wb.dispose();
        }
        
    }
    
    // 엑셀파일 생성
    public static void makeExlFile(List<Map> sheetList, String filePath, String fileName, String isProtected, String isTree) throws IOException
    {
        SXSSFWorkbook wb = new SXSSFWorkbook(100);
        
        for(Map sheetMap:sheetList){
            String sheetName = String.valueOf(sheetMap.get("SHEETNAME"));
            List headList = (List) sheetMap.get("HEADLIST");
            List data = (List) sheetMap.get("RESULTLIST");
            
            if("".equals(sheetName)) sheetName="sheet";
            SXSSFSheet sheet = wb.createSheet(sheetName);
            
            wb = makeSheet(sheet, headList, data, isProtected, isTree);
        }
        
        // 만약 taconfig에 지정해놓은 FILE_DIR 폴더가 없으면 생성
        File dir = new File(filePath);
        if(!dir.exists())
        {
            dir.mkdirs();
        }
        
        //File file = new File(filePath + "\\" + fileName); // Linux Server에서 에러 발생 
        File file = new File(filePath +  fileName);
        FileOutputStream fOut = new FileOutputStream(file);
        
        try
        {
            wb.write(fOut);
            fOut.close();
            wb.dispose();
        }
        catch (Exception e) {
            fOut.close();
            wb.dispose();
        }
        
    }
    
    private static SXSSFWorkbook makeSheet(SXSSFSheet sheet, List headList, List data, String isProtected, String isTree) throws IOException
    {
        List newHeadList = new ArrayList();
        
        SXSSFWorkbook wb = sheet.getWorkbook();
        DataFormat df = wb.createDataFormat();
        
        //Excel File 암호화 여부 
        if("Y".equals(MwareConfig.getIsLockExcelDownFile()) && !"N".equals(isProtected))
        {
            sheet.protectSheet(MwareConfig.getIsLockExcelPassword());
        }
        
        //default 스타일 객체 생성
        CellStyle defaultStyle = wb.createCellStyle();
        defaultStyle.setDataFormat(df.getFormat("@"));// @ : text 포맷
        
        //스타일 객체 생성
        CellStyle style = wb.createCellStyle();
        
        float[] hsb = Color.RGBtoHSB(204, 255, 204, null);
        
        XSSFColor color = new XSSFColor( Color.white );
        XSSFColor blackC = new XSSFColor(Color.getHSBColor(hsb[0], hsb[1], hsb[2]) );
        //셀 스타일
        style.setFillBackgroundColor(IndexedColors.WHITE.getIndex());
        style.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setDataFormat(df.getFormat("@"));// @ : text 포맷
        //폰트 객체 생성
        Font font = wb.createFont(); 
        
        font.setFontHeightInPoints((short)10); //폰트 크기
        font.setBold(true);
        
        // Header Set
        SXSSFRow headerRow = sheet.createRow(0);
        
        //셀 높이 설정
        headerRow.setHeight((short)512);
        
        Map<String, String> headMapA = new LinkedHashMap<String, String>();
        int k = 0;
        int headerCnt = 1;
        SXSSFCell cell = null;
        
        //make empty added head list
        List addHeadList = new ArrayList<>();
        Map firstHeadMap = (Map) headList.get(0);
        if(firstHeadMap.containsKey("ADDHEADS")){
            List firstAddHeads = (List) firstHeadMap.get("ADDHEADS");
            for(int i=0; i<firstAddHeads.size(); i++) {
                addHeadList.add(i, new ArrayList<>());
            }
        }
        
        for(Object obj : headList)
        {
            Map headMap = (Map)obj;
            
            String hidden = String.valueOf(headMap.get("HIDDEN"));
            String colId = String.valueOf(headMap.get("ID")).toUpperCase();
            String colName = String.valueOf(headMap.get("VALUE"));
            String colWidth = String.valueOf(headMap.get("WIDTH"));

            if("FALSE".equals(hidden.toUpperCase()) && !"ISDELCHECK".equals(colId.toUpperCase()))
            {
                sheet.setDefaultColumnStyle(k, defaultStyle);//헤더를 만들면서 컬럼에 default style 적용
                
                cell = headerRow.createCell(k);
                
                if(headMap.containsKey("SPANS")){
                    List spans = (List) headMap.get("SPANS");
                    int rowSpan = ((Double) spans.get(0)).intValue();
                    int colSpan = ((Double) spans.get(1)).intValue();
                    if(rowSpan>1 || colSpan>1) sheet.addMergedRegion(new CellRangeAddress(0, 0+colSpan-1, k, k+rowSpan-1));
                }
                
                cell.setCellStyle(style);
                cell.setCellValue(colName);
 
                headMapA.put(colId.toUpperCase(), colName);

                int colW = Integer.parseInt(colWidth) * 40;

                sheet.setColumnWidth(k, colW);
                
                newHeadList.add(obj);
                
                k++;
                
                if(headMap.containsKey("ADDHEADS")){
                    List addHeads = (List) headMap.get("ADDHEADS");
                    for(int i=0; i<addHeads.size(); i++) {
                        Map addHead = (Map) addHeads.get(i);
                        List addHeadRow = (List) addHeadList.get(i);
                        addHeadRow.add(addHead);
                    }
                }
            }
            
        }
        
        for(Object obj:addHeadList)
        {
            List addHeadRow = (List) obj;
            headerRow = sheet.createRow(headerCnt);
            //셀 높이 설정
            headerRow.setHeight((short)512);
            int headerColCnt = 0;
            for(Object obj2 : addHeadRow)
            {
                Map headMap = (Map)obj2;
                
                String colName = String.valueOf(headMap.get("VALUE"));
                
                cell = headerRow.createCell(headerColCnt);
                
                if(headMap.containsKey("SPANS")){
                    List spans = (List) headMap.get("SPANS");
                    int rowSpan = ((Double) spans.get(0)).intValue();
                    int colSpan = ((Double) spans.get(1)).intValue();
                    if(rowSpan>1 || colSpan>1) sheet.addMergedRegion(new CellRangeAddress(headerCnt, headerCnt+colSpan-1, headerColCnt, headerColCnt+rowSpan-1));
                }
                
                cell.setCellStyle(style);
                cell.setCellValue(colName);
                
                headerColCnt++;
            }
            headerCnt++;
        }
        
        sheet.createFreezePane( 0, headerCnt, 0, headerCnt );

        int i = 0;
        String headKey;
        String cellAlign;
        SXSSFRow dataRow;
        CellStyle cenStyle = wb.createCellStyle();
        cenStyle.setAlignment(HorizontalAlignment.CENTER);
        cenStyle.setBorderBottom(BorderStyle.THIN);
        cenStyle.setBorderLeft(BorderStyle.THIN);
        cenStyle.setBorderRight(BorderStyle.THIN);
        cenStyle.setDataFormat(df.getFormat("@"));// @ : text 포맷
        CellStyle leftStyle = wb.createCellStyle();
        leftStyle.setAlignment(HorizontalAlignment.LEFT);
        leftStyle.setBorderBottom(BorderStyle.THIN);
        leftStyle.setBorderLeft(BorderStyle.THIN);
        leftStyle.setBorderRight(BorderStyle.THIN);
        leftStyle.setDataFormat(df.getFormat("@"));// @ : text 포맷
        CellStyle rightStyle = wb.createCellStyle();
        rightStyle.setAlignment(HorizontalAlignment.RIGHT);
        rightStyle.setBorderBottom(BorderStyle.THIN);
        rightStyle.setBorderLeft(BorderStyle.THIN);
        rightStyle.setBorderRight(BorderStyle.THIN);
        rightStyle.setDataFormat(df.getFormat("@"));// @ : text 포맷
        HorizontalAlignment al;
        
        //트리형식일때
        if("Y".equals(isTree)) data = makeTreeExcelList(data);
        
        for(Object obj : data)
        {
            dataRow = sheet.createRow(headerCnt+i);
            
            Map record = (Map)obj;

            int j = 0;
            int h = 0;
            for(Object headRowObj : newHeadList)
            {
                Map headRowMap = (Map)headRowObj;
                headKey = String.valueOf(headRowMap.get("ID"));
                int lvl = 0;
                
                //트리형식일때 
                if("Y".equals(isTree))
                   for( Object keyObj : record.keySet() )
                    {
                        String key = String.valueOf(keyObj);
                        
                        if("LVL".equals(key.toUpperCase())) lvl = Integer.parseInt(String.valueOf(record.get(key.toUpperCase())));

                    }
                
                String colVal = "";
                if("SEQNO".equals(headKey.toUpperCase())) {
                    colVal = colVal = i+1+"";
                }
                else {
                    for( Object keyObj : record.keySet() )
                    {
                        String key = String.valueOf(keyObj);
                        
                        if(!headKey.toUpperCase().equals(key.toUpperCase())) continue;
                        
                        colVal = String.valueOf(record.get(key.toUpperCase()));
                        if(colVal == "null") colVal = "";
                    }
                }
                cellAlign = String.valueOf(headRowMap.get("ALIGN"));
                
                cell = dataRow.createCell(h); 
                
                if("center".equals(cellAlign.toLowerCase()))cell.setCellStyle(cenStyle);
                else if("right".equals(cellAlign.toLowerCase()))cell.setCellStyle(rightStyle);
                else cell.setCellStyle(leftStyle);
                
                cell.setCellValue(colVal);
                
                //트리형식일때
                if("Y".equals(isTree) && "tree".equals(String.valueOf(headRowMap.get("TYPE"))))
                {
                    CellStyle temStyle = wb.createCellStyle();
                    temStyle.setIndention((short)lvl);
                    
                    cell.setCellStyle(temStyle);
                }
                
                h++;
                
            }
            
            i++;
        }
        return wb;
    }
    
    /**
     * 엑셀 다운로드
     * @author js.lee
     * @since   1.0
     *
     * @param filePath
     * @param fileName
     * @param req
     * @param res
     * @param currentPageId
     * @throws IOException
     */
    public static void exportExlFile(String filePath, String fileName , HttpServletRequest req, HttpServletResponse res, String currentPageId) throws IOException
    {
        if (!"".equals(currentPageId) && currentPageId != null) {
            javax.servlet.http.Cookie c = new javax.servlet.http.Cookie("fileDownloadToken_"+currentPageId, "TRUE");
            res.addCookie(c);
        } else {
            javax.servlet.http.Cookie c = new javax.servlet.http.Cookie("fileDownloadToken", "TRUE");
            res.addCookie(c);
        }
        
        res.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        res.setHeader("Content-Disposition", "attachment; filename=" + K2E(fileName));
        res.setCharacterEncoding("UTF-8");
        res.setHeader("Expires:", "-1"); // eliminates browser caching
        res.setHeader("Cache-Control", "max-age=0");
        
        File file = new File(filePath+ fileName);
        
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream bos = new BufferedOutputStream(res.getOutputStream());
        
        byte[] data2 = new byte[2048];
        int input = 0;
        while((input = bis.read(data2))!= -1){
         bos.write(data2, 0, input);
         bos.flush();
        }
        
        if(bos!=null) bos.close();
        if(bis!=null) bis.close();
        
    }
    
    /**
     * 엑셀 파일 삭제
     * @author js.lee
     * @since   1.0
     *
     * @param filePath
     * @param fileName
     * @throws IOException
     */
    public static void deleteExlFile(String filePath, String fileName) throws IOException
    {
        File file = new File(filePath + fileName);
        
        if (file.exists()) {
            file.delete();
        }
    }
    
    
    
    public static final String KOR_CHARSET = "MS949"; //EUC-KR
       public static final String ENG_CHARSET = "ISO-8859-1";
       private static String K2E(String korean) 
       {
            String english = null;
            
            if (korean == null ) {
                return null;
            }
            
            try { 
                english = new String(korean.getBytes(KOR_CHARSET), ENG_CHARSET);
            } catch (UnsupportedEncodingException e) {
                english = new String(korean);
            }

            return english;
        }
       
    private static void transport(InputStream in, OutputStream out)
            throws IOException 
            {
                    
                BufferedInputStream bin = null;
                BufferedOutputStream bos = null;
                
                try 
                {
                    bin = new BufferedInputStream( in );
                    bos = new BufferedOutputStream( out );
                
                    byte[] buf = new byte[2048]; //buffer size 2K.
                    int read = 0;
//                  while ((read = bin.read(buf)) != -1) 
//                  {
//                      bos.write(buf,0,read);
//                  }

                    while ((read = bin.read()) != -1) {
                        bos.write(read);
                    }
                    
                    bos.flush();
                    bin.close();
                    
                }
                finally 
                {
                    bos.flush();
                    bin.close();
                }        
            }
    

    /**
     * Execute Query with Objects
     * @param driveName
     * @param dbUrl
     * @param dbUser
     * @param dbPass
     * @param exeQuery
     * @param objArr
     * @throws IOException
     */
    public static void jdbcConnUpdate(String driveName, String dbUrl, String dbUser, String dbPass , String exeQuery, Object[] objArr) throws IOException
    {
        //jdbc:oracle:thin:@10.122.246.190:1521:gmms
        String DB_URL = dbUrl;
        String DB_USER = dbUser;
        String DB_PASSWORD = dbPass;

        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            // 드라이버를 로딩한다. 
            /**
             *  MSSQL com.microsoft.sqlserver.jdbc.SQLServerDriver  jdbc:sqlserver://10.214.101.22:1433;databaseName=SEIS
             *  MySQL com.mysql.jdbc.Driver jdbc:mysql://hostname/ databaseName 
                ORACLE oracle.jdbc.driver.OracleDriver jdbc:oracle:thin:@hostname:port Number:databaseName 
                DB2 COM.ibm.db2.jdbc.net.DB2Driver jdbc:db2:hostname:port Number/databaseName 
                Sybase com.sybase.jdbc.SybDriver jdbc:sybase:Tds:hostname: port Number/databaseName 
             */
            Class.forName(driveName);
        } catch (ClassNotFoundException e ) {
            e.printStackTrace();
        }

        try {
            // 데이터베이스의 연결을 설정한다.
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement  = conn.prepareStatement(exeQuery);
            int i = 0;
            for(Object obj : objArr)
            {
                i++;
                preparedStatement.setString(i, String.valueOf(obj));
            }
            
            preparedStatement.executeUpdate();
            
            // SQL문을 실행한다.
           // rs = stmt.executeQuery(query);

        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            try {
                // Statement를 닫는다.
                preparedStatement.close();
                // Connection를 닫는다.
                conn.close();
            } catch ( SQLException e ) {}
        }

    }
    
    public static void jdbcConnBatch(String driveName, String dbUrl, String dbUser, String dbPass , String exeQuery, List<Object[]> paramList) throws IOException
    {
        //jdbc:oracle:thin:@10.122.246.190:1521:gmms
        String DB_URL = dbUrl;
        String DB_USER = dbUser;
        String DB_PASSWORD = dbPass;

        Connection conn = null;
        PreparedStatement preparedStatement = null;
//        ResultSet rs = null;

        try {
            // 드라이버를 로딩한다. 
            /**
             *  MySQL com.mysql.jdbc.Driver jdbc:mysql://hostname/ databaseName 
                ORACLE oracle.jdbc.driver.OracleDriver jdbc:oracle:thin:@hostname:port Number:databaseName 
                DB2 COM.ibm.db2.jdbc.net.DB2Driver jdbc:db2:hostname:port Number/databaseName 
                Sybase com.sybase.jdbc.SybDriver jdbc:sybase:Tds:hostname: port Number/databaseName 
             */
            Class.forName(driveName);
        } catch (ClassNotFoundException e ) {
            e.printStackTrace();
        }

        try {
            // 데이터베이스의 연결을 설정한다.
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            conn.setAutoCommit(false);
            
            // Statement를 가져온다.
            //stmt = conn.createStatement();

            preparedStatement  = conn.prepareStatement(exeQuery);
            int j = 0;
            for(Object[] objArr : paramList)
            {
                int i = 0;
                for(Object obj : objArr)
                {
                    i++;
                    preparedStatement.setString(i, String.valueOf(obj));
                }
                preparedStatement.addBatch();
            }
                preparedStatement.executeBatch();
                conn.commit();
            
            // SQL문을 실행한다.
           // rs = stmt.executeQuery(query);

        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            try {
                // ResultSet를 닫는다.
//                rs.close();
                // Statement를 닫는다.
                preparedStatement.close();
                // Connection를 닫는다.
                conn.close();
            } catch ( SQLException e ) {}
        }

    }
    
    /**
     * Execute Query 
     * @param driveName
     * @param dbUrl
     * @param dbUser
     * @param dbPass
     * @param exeQuery
     * @throws IOException
     */
    public static void jdbcConnUpdate(String driveName, String dbUrl, String dbUser, String dbPass , String exeQuery) throws IOException
    {
        //jdbc:oracle:thin:@10.122.246.190:1521:gmms
        String DB_URL = dbUrl;
        String DB_USER = dbUser;
        String DB_PASSWORD = dbPass;

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 드라이버를 로딩한다. 
            /**
             *  MySQL com.mysql.jdbc.Driver jdbc:mysql://hostname/ databaseName 
                ORACLE oracle.jdbc.driver.OracleDriver jdbc:oracle:thin:@hostname:port Number:databaseName 
                DB2 COM.ibm.db2.jdbc.net.DB2Driver jdbc:db2:hostname:port Number/databaseName 
                Sybase com.sybase.jdbc.SybDriver jdbc:sybase:Tds:hostname: port Number/databaseName 
             */
            Class.forName(driveName);
        } catch (ClassNotFoundException e ) {
            e.printStackTrace();
        }

        try {
            // 데이터베이스의 연결을 설정한다.
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Statement를 가져온다.
            stmt = conn.createStatement();

            // SQL문을 실행한다.
            rs = stmt.executeQuery(exeQuery);

        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            try {
                // ResultSet를 닫는다.
                rs.close();
                // Statement를 닫는다.
                stmt.close();
                // Connection를 닫는다.
                conn.close();
            } catch ( SQLException e ) {}
        }

    }

    /**
     * Execute For List
     * @param driveName
     * @param dbUrl
     * @param dbUser
     * @param dbPass
     * @param exeQuery
     * @return
     * @throws ClassNotFoundException 
     * @throws SQLException 
     */
    public static List jdbcConnForList(String driveName, String dbUrl, String dbUser, String dbPass , String exeQuery) throws ClassNotFoundException, SQLException
    {
        //jdbc:oracle:thin:@10.122.246.190:1521:gmms
        String DB_URL = dbUrl;
        String DB_USER = dbUser;
        String DB_PASSWORD = dbPass;

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        List resultList = null;
        
        try {
            // 드라이버를 로딩한다. 
            /**
             *  MySQL com.mysql.jdbc.Driver jdbc:mysql://hostname/ databaseName 
                ORACLE oracle.jdbc.driver.OracleDriver jdbc:oracle:thin:@hostname:port Number:databaseName 
                DB2 COM.ibm.db2.jdbc.net.DB2Driver jdbc:db2:hostname:port Number/databaseName 
                Sybase com.sybase.jdbc.SybDriver jdbc:sybase:Tds:hostname: port Number/databaseName 
             */
            Class.forName(driveName);
        } catch (ClassNotFoundException e ) {
            e.printStackTrace();
            throw e;
        }

        try {

            // 데이터베이스의 연결을 설정한다.
//          System.out.println("DB_URL:"+DB_URL+"     DB_USER:"+DB_USER+"     DB_PASSWORD:"+DB_PASSWORD);
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Statement를 가져온다.
            stmt = conn.createStatement();

            // SQL문을 실행한다.
            rs = stmt.executeQuery(exeQuery);
            
            resultList= getResultMapRows(rs);
//            while (rs.next()) { 
//                String empno = rs.getString(1);
//                // 결과를 출력한다.
//                   empno ); 
//            }

        } catch ( SQLException e ) {
            e.printStackTrace();
            throw e;
        } catch ( Exception e ) {
            e.printStackTrace();
        } finally {
            try {
                // ResultSet를 닫는다.
                rs.close();
                // Statement를 닫는다.
                stmt.close();
                // Connection를 닫는다.
                conn.close();
            } catch ( Exception e ) {}
        }

        return resultList;
    }
    
    public static List jdbcConnForList(String driveName, String dbUrl, String dbUser, String dbPass , String exeQuery, Object[] objArr) throws ClassNotFoundException, SQLException
    {
        //jdbc:oracle:thin:@10.122.246.190:1521:gmms
        String DB_URL = dbUrl;
        String DB_USER = dbUser;
        String DB_PASSWORD = dbPass;

        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        List resultList = null;
        
        try {
            // 드라이버를 로딩한다. 
            /**
             *  MySQL com.mysql.jdbc.Driver jdbc:mysql://hostname/ databaseName 
                ORACLE oracle.jdbc.driver.OracleDriver jdbc:oracle:thin:@hostname:port Number:databaseName 
                DB2 COM.ibm.db2.jdbc.net.DB2Driver jdbc:db2:hostname:port Number/databaseName 
                Sybase com.sybase.jdbc.SybDriver jdbc:sybase:Tds:hostname: port Number/databaseName 
             */
            Class.forName(driveName);
        } catch (ClassNotFoundException e ) {
            e.printStackTrace();
            throw e;
        }

        try {

            // 데이터베이스의 연결을 설정한다.
//          System.out.println("DB_URL:"+DB_URL+"     DB_USER:"+DB_USER+"     DB_PASSWORD:"+DB_PASSWORD);
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
            preparedStatement  = conn.prepareStatement(exeQuery);
            int i = 0;
            for(Object obj : objArr)
            {
                i++;
                preparedStatement.setString(i, String.valueOf(obj));
            }
            
            rs = preparedStatement.executeQuery();

            resultList= getResultMapRows(rs);

        } catch ( SQLException e ) {
            e.printStackTrace();
            throw e;
        } catch ( Exception e ) {
            e.printStackTrace();
        }  finally {
            try {
                // ResultSet를 닫는다.
                rs.close();
                // Statement를 닫는다.
                preparedStatement.close();
                // Connection를 닫는다.
                conn.close();
            } catch ( Exception e ) {}
        }

        return resultList;
    }
    
    
    private static List<Map> getResultMapRows(ResultSet rs) throws Exception 
    { 
        // ResultSet 의 MetaData를 가져온다. 
        ResultSetMetaData metaData = rs.getMetaData(); 
        // ResultSet 의 Column의 갯수를 가져온다. 
        int sizeOfColumn = metaData.getColumnCount(); 

        List<Map> list = new ArrayList<Map>(); 
        Map<String, Object> map; 
        String column; 
        // rs의 내용을 돌려준다. 
        while (rs.next()) 
        { 
            // 내부에서 map을 초기화 
            map = new HashMap<String, Object>(); 
            // Column의 갯수만큼 회전 
            for (int indexOfcolumn = 0; indexOfcolumn < sizeOfColumn; indexOfcolumn++) 
            { 
                column = metaData.getColumnName(indexOfcolumn + 1); 
                // map에 값을 입력 map.put(columnName, columnName으로 getString) 
                map.put(column, rs.getString(column)); 
            } 
            // list에 저장 
            list.add(map); 
        } 
        return list; 
    }
    
    /**
     * AC LOV Header
     * @param resultList
     * @param headerList
     * @return
     */
    public static String makeHeaderForAcLov(List resultList, List headerList )
    {
      List headList = new ArrayList();
      
      //데이터 결과  리스트의 수량이 0보다 크면...
      if(resultList.size() > 0)
      {
          //첫번째 Row를 추출해서 iterator를 만듬.
          Iterator headIter = ((Map)resultList.get(0)).keySet().iterator();
          int listSize = ((Map)resultList.get(0)).keySet().size();

          Map hMap = null;
          while(headIter.hasNext())
          {
              //조회된 값의 column ID와 Header 정보의 Column ID를 비교하여 같은 값이 있는지 확인한다.
              String headkey = (String)headIter.next();
              boolean flag = false;
              for(int i = 0; headerList.size() > i ; i++)
              {
                  hMap = (Map)headerList.get(i);
                  
                  if(headkey.toUpperCase().equals(String.valueOf(hMap.get("ID")).toUpperCase()))
                  {
                      flag = true;
                      break;
                  }
              }
              
              //조회된 값의 Column Id와 Header의 Column ID가 같다면 Header로 세팅한다.
              if(flag)
              {
                  Iterator iter = hMap.keySet().iterator();
                  Map headMap = new HashMap();
                  
                  while(iter.hasNext())
                  {
                      String key = (String)iter.next();
                      String value = String.valueOf(hMap.get(key));
                      
                      if(key.toLowerCase().equals("id")) value = value.toUpperCase();

                      headMap.put(key.toLowerCase(), value == null?"":value);
                      
                      headMap.remove("height");
                  }
                  
                  //header 정보에 조회된 Header List를 세팅한다.
                  headList.add(headMap);
              }
              
              //Header 정보에 Column Id가 없다면 화면에 표시되지 않는다.
             
          }
      }
      //결과 List의 항목이 1개도 없을경우...Head List의 값으로 Head 세팅
      else
      {
          for(int i = 0; headerList.size() > i ; i++)
          {
              Map test = (Map)headerList.get(i);
              Iterator iter = test.keySet().iterator();
              Map headMap = new HashMap();
              
              while(iter.hasNext())
              {
                  String key = (String)iter.next();
                  String value = String.valueOf(test.get(key));
                  
                  if(key.toLowerCase().equals("id")) value = value.toUpperCase();

                  headMap.put(key.toLowerCase(), value == null?"":value);
                  
                  headMap.remove("height");
              }
              headList.add(headMap); 
          }
      }
      
      Map testmap = new HashMap();
      testmap.put("head", headList);
      testmap.put("data",makeJson(resultList));
      
      Gson gson = new Gson();
      String dataJString = gson.toJson(makeJson(resultList));
      
      dataJString = dataJString.replaceAll("\"ID\"", "id");
      dataJString = dataJString.replaceAll("\"OPEN\"", "open");
      dataJString = dataJString.replaceAll("\"false\"", "false");
      dataJString = dataJString.replaceAll("\"true\"", "true");
      dataJString = dataJString.replaceAll("\"ROWS\"", "rows");
      dataJString = dataJString.replaceAll("\"rows\"", "rows");
      
      StringBuffer strB = new StringBuffer();
      
      strB.append("{\"data\":");
      strB.append(dataJString);
      strB.append(",\"head\":");
      strB.append(gson.toJson(headList));
      strB.append("}");
      
      return strB.toString();
        
    }
    
    public static String aesEncodeString(String str){
        String encodeStr = "";
        try {
            encodeStr = AES256Cipher2.getInstance().AES_Encode(str);
        } catch (Exception e) {
            //ETC- ServerJRE 폴더를 참조하세요.
            e.printStackTrace();
        }
        
        return encodeStr;
    }
    
    public static String aesDecodeString(String str){
        String decodeStr = "";
        try {
            decodeStr = AES256Cipher2.getInstance().AES_Decode(str);
        } catch (Exception e) {
            //ETC- ServerJRE 폴더를 참조하세요.
            e.printStackTrace();
        }
        
        return decodeStr;
    }
    
    public static String fillStr(String str, String fillStr, int length){
        int strLength = str.length(); 
        StringBuffer sb = new StringBuffer();
        
        if(strLength>0 & strLength<length){
            int diffLength = length - strLength; 
            for (int i = 0; i < diffLength; i++) {
                sb.append(fillStr);
            }
        }
        
        sb.append(str);
        return sb.toString();
    }
    
    public static List makeHeader(List resultList)
    {
        List headList = new ArrayList();
        
        if(resultList.size() > 0)
        {
            Iterator headIter = ((Map)resultList.get(0)).keySet().iterator();
            int listSize = ((Map)resultList.get(0)).keySet().size();
            
            while(headIter.hasNext())
            {
                Map headMap = new HashMap();
                
                String headkey = (String)headIter.next();
                headMap.put("ID", headkey);
                headMap.put("WIDTH", Math.round(1300/listSize));
                headMap.put("TYPE", headkey.equals("ISDELCHECK")?"ch":"ro");
                headMap.put("ALIGN", "left");
                headMap.put("SORT", "str");
                headMap.put("HIDDEN", "false");
                headMap.put("VALUE",headkey);
                
                headList.add(headMap);
            }
        }
        
        return headList;
    }
    
    public static List setGridMap(HttpServletRequest request)
    {
        String gridIds = request.getParameterValues("ids")[0];
        String[] gridIdArr = gridIds.split(",");
        
        Map paramMap = request.getParameterMap();
        Iterator<String> keys = null;
        
        List returnList = new ArrayList();
        Map parameterMap = null;
        
        for(String gridId : gridIdArr)
        {
            parameterMap = new HashMap();
            keys = paramMap.keySet().iterator();
            while( keys.hasNext() ){
                String key = keys.next();
                
                if(key.indexOf("_") > 0)
                {
                    String[] idNColumnId = key.split("_");
                    if(gridId.equals(idNColumnId[0]))
                    {
                        parameterMap.put(idNColumnId[1].replace("!", ""), ((String[])paramMap.get(key))[0]);
                    }
                }

            }
            
            returnList.add(parameterMap);
        }
        
        return returnList;
    }



    /**
     * data에 -와 :가 붙여져서 있으면 빼주고,
     * 없으면 년월일 시분초로 나누어서 -와 :를 붙여준다.
     * @author  nhkim8548
     * @version $Id$
     * @since   1.0
     * 
     * @param data
     * @return
     */
    public static String convertDateTime(String data)
    {
        String result = "";

        int tempHyphenIndex = data.indexOf("-");
        int tempColonIndex = data.indexOf(":");
        
        // -1 이라면 '-'과 ':'이 없는 경우이다.
        if (tempHyphenIndex == -1 && tempColonIndex == -1 && data.length() == 14)
        {
        	if(data.length() > 8) {
        		result = convertDate(data.substring(0, 8)) +" "+ convertTime(data.substring(8)) ;
        	} else if(data.length() == 8){
        		result = convertDate(data.substring(0, 8));
        	} else {
        		result = convertDate(data);
        	}
        }
        else
        {
            result = data.replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "");
        }
        
        return result;
        
    }
    
    public static boolean isValidEmailAddress(String email){
        boolean err = false;
        //  String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";  // xxxxxx@daewoong-eng.com이 invalid mail로 됨. 그래서 변경 20181018 
        String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
          Pattern p = Pattern.compile(regex);
          Matcher m = p.matcher(email);
          if(m.matches()) {
           err = true; 
          }
          return err;
    }
    
    public static String setDecoBeanId(String initBeanId,User user)
    {
        List<Map> decoList = MwareConfig.getDecoMapList();
        String compNo = user.getCompNo();
        
        for(Map decoMap : decoList)
        {
            if(initBeanId.equals(decoMap.get("BEAN_NO")) && compNo.equals(decoMap.get("COMP_NO")))
            {
                initBeanId = "null".equals(String.valueOf(decoMap.get("PACKAGE_NO")))?initBeanId:String.valueOf(decoMap.get("PACKAGE_NO"))+initBeanId;
                break;
            }
        }
        
        System.out.println("Decorator Pattern Applied:"+initBeanId);
        return initBeanId;
    }
    
    public static String setDecoBeanId(String initBeanId,String compNo)
    {
        List<Map> decoList = MwareConfig.getDecoMapList();

        for(Map decoMap : decoList)
        {
            if(initBeanId.equals(decoMap.get("BEAN_NO")) && compNo.equals(decoMap.get("COMP_NO")))
            {
                initBeanId = "null".equals(String.valueOf(decoMap.get("PACKAGE_NO")))?initBeanId:String.valueOf(decoMap.get("PACKAGE_NO"))+initBeanId;
                break;
            }
        }
        
        System.out.println("Decorator Pattern Applied:"+initBeanId);
        return initBeanId;
    }
    
    public static Object getBean(String name, HttpServletRequest request)
    {
    	return getBean(name, getUser(request));
    }
    
    public static Object getBean(String name, User user)
    {
        return getBean(setDecoBeanId(name, user));
    }
    
    public static Object getBean(String beanId) {
        
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
 
        if( applicationContext == null ) {
            throw new NullPointerException("Spring의 ApplicationContext초기화 안됨");
        }

        return applicationContext.getBean(beanId);
    }

    public static Object getBean(String name, String compNo)
    {
        return getBean(setDecoBeanId(name, compNo));
        
    }
    
    /**
     * make 1 sheet excel
     * 
     * @param resultList
     * @param request
     * @param response
     * @param baseForm
     * @throws IOException
     */
    public static void makeGridExport(List resultList, HttpServletRequest request,HttpServletResponse response,BaseForm baseForm) throws IOException
    {
        ConfigService configService = (ConfigService) BaseAction.ctx.getBean("configService");      
        Map resultMap = null;
        
        Gson gson = new Gson();
        
        List<Map> headList = gson.fromJson(baseForm.getHeadList(), new TypeToken<List<Map>>(){}.getType());
        
        if(headList == null || headList.size() == 0){
            headList = CommonUtil.makeHeader(resultList);
        }
        
        // TODO Auto-generated method stub
        request.setAttribute("resultList", resultList);
        request.setAttribute("headList", headList);
        request.setAttribute("fileName", baseForm.getFileName());
        request.setAttribute("currentPageId", baseForm.getCurrentPageId());
        request.setAttribute("isTree", "N");
        request.setAttribute("user", getUser(request));
        
    }
    
    /**
     * make multi sheet excel
     * 
     * @param sheetList
     * @param request
     * @param response
     * @param baseForm
     * @throws IOException
     */
    public static void makeMultiGridExport(List sheetList, HttpServletRequest request,HttpServletResponse response,BaseForm baseForm) throws IOException
    {
        request.setAttribute("sheetList", sheetList);
        request.setAttribute("fileName", baseForm.getFileName());
        request.setAttribute("currentPageId", baseForm.getCurrentPageId());
        request.setAttribute("isTree", "N");
        request.setAttribute("user", getUser(request));
        request.setAttribute("isMultiSheet", "Y");
    }
    
    /**
     * make 1 sheet tree excel
     * 
     * @param resultList
     * @param request
     * @param response
     * @param baseForm
     * @throws IOException
     */
    public static void makeTreeGridExport(List resultList, HttpServletRequest request,HttpServletResponse response,BaseForm baseForm) throws IOException
    {
        ConfigService configService = (ConfigService) BaseAction.ctx.getBean("configService");      
        Map resultMap = null;
        
        Gson gson = new Gson();
        
        List<Map> headList = gson.fromJson(baseForm.getHeadList(), new TypeToken<List<Map>>(){}.getType());
        
        if(headList == null || headList.size() == 0){
            headList = CommonUtil.makeHeader(resultList);
        }
        
        // TODO Auto-generated method stub
        request.setAttribute("resultList", resultList);
        request.setAttribute("headList", headList);
        request.setAttribute("fileName", baseForm.getFileName());
        request.setAttribute("currentPageId", baseForm.getCurrentPageId());
        request.setAttribute("isTree", "Y");
        request.setAttribute("user", getUser(request));
        
    }
    
    /**
     * 현재 로그인 유저 리턴
     * 
     * @return
     */
    public static User getUser()
    {
        User user = null;
        if(RequestContextHolder.getRequestAttributes() == null)
        {
            String language = "en"; //기본 랭귀지 설정 ?!!!?!!?!?!?
            Locale locale = new Locale(language);
            user = new User();
            user.setLangId(language);
            user.setLocale(locale);
        }
        else
        {                
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            user = getUser(request);
        }
        return user;
    }
    
    /**
     * 현재 로그인 유저 리턴
     * @author  javaworker
     * @version $Id: BaseAction.java,v 1.2 2015/01/09 00:16:42 pochul2423 Exp $
     * @since   1.0
     * 
     * @param request
     * @return
     */
    public static User getUser(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        
        User user = (User) session.getAttribute(request.getSession().getId());
        if(user == null)
        {
        	ConfigService configService = (ConfigService)getBean("configService");
            String language = configService.findLanguage();//"en"; //기본 랭귀지 설정 ?!!!?!!?!?!?
            Locale locale = new Locale(language);
            user = new User();
            user.setLangId(language);
            user.setLocale(locale);
        }
        return user;
    }
    
    public static void makeJsonResult(List resultList, HttpServletRequest request,  HttpServletResponse response, String totalCount) throws IOException
    {
        Map result = null;
        String isHeaderLoaded = (String)request.getParameter("isHeaderLoaded");
        String firstRow = (String)request.getParameter("firstRow");
        String totCount = (String)request.getParameter("isTotalCount");

        if("".equals(totalCount))totalCount = "0";
        
        if("null".equals(firstRow) || firstRow == null ) firstRow = "0";
        if("null".equals(totCount) || totCount == null || "0".equals(totCount)) totCount = totalCount;
        
        result = CommonUtil.makeResultJson(resultList); //아니면 그냥 

        String totcnt;
        if((Integer.parseInt(firstRow) + Integer.parseInt(MwareConfig.getGridMaxLoadCount())) < Integer.parseInt(totCount)) totcnt = (Integer.parseInt(firstRow) + Integer.parseInt(MwareConfig.getGridMaxLoadCount()))+"";
        else totcnt = totCount;
  
        result.put("view_count", totCount);
        result.put("total_count",totcnt);
        result.put("pos", firstRow);
        
        String jsonString = CommonUtil.makeJsonString(result);

       // logger.debug(jsonString);

        response.getWriter().print(jsonString);
    }
    
    public static void makeTreeJsonResult(List resultList, HttpServletRequest request,  HttpServletResponse response, String totalCount) throws IOException
    {
        List result = makeTreeResultJson(resultList);
        
        Gson gson = new Gson();
        
        String jsonString = gson.toJson(result);

        jsonString = jsonString.replaceAll("\"ID\"", "\"id\"");
        jsonString = jsonString.replaceAll("\"OPEN\"", "\"open\"");
        jsonString = jsonString.replaceAll("\"ROWS\"", "\"rows\"");
        
        response.getWriter().print(jsonString);

    }
    
    public static void makeJsonResult(ResponseDTO responseDTO, HttpServletResponse response) throws IOException
    {
        responseDTO.setData(makeJson(responseDTO.getData()));
        response.getWriter().print(new Gson().toJson(responseDTO));
        response.setStatus(responseDTO.getStatus());
    }
    
    /**
     * 배열내에 문자열이 있는지 검사
     * @param arr
     * @param target
     * @return
     */
    public static boolean isUseStringInArray(String[] arr, String target){
    	return Arrays.asList(arr).contains(target);
    }
    
    /**
     * 배열을 콤마가 찍힌 문자열로 변환
     * @param arr
     * @param returnEmptyStr
     * @return
     */
    public static String getCommaStringFromArray(String[] arr, String returnEmptyStr){
		if (arr.length > 0) {
		    StringBuilder nameBuilder = new StringBuilder();

		    for (String n : arr) {
		        nameBuilder.append("'").append(n.replace("'", "\\'")).append("',");
		    }
		    nameBuilder.deleteCharAt(nameBuilder.length() - 1);

		    return nameBuilder.toString();
		} else {
		    return returnEmptyStr;
		}
	}
    
    /**
     * 인터페이스 서비스 call
     * tacompfunc에 BatchService 먼저 등록해야함
     * @param methodName
     * @param arguments
     * @param user
     * @return
     */
    public static void intfCall(String methodName, Map arguments, User user) throws SqlIgnoreException
    {
        try {
            Object batchService = CommonUtil.getBean("BatchService", user);
            Method method = batchService.getClass().getMethod(methodName, CommonBatchDTO.class);
            CommonBatchDTO commonBatchDTO = new CommonBatchDTO();
            commonBatchDTO.setExecType(commonBatchDTO.MANUAL);
            commonBatchDTO.setUser(user);
            commonBatchDTO.setArguments(arguments);
            method.invoke(batchService, commonBatchDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SqlIgnoreException(e.getMessage());
        }
    }
    
    /**
     * 숫자를 제외한 모든 특수문자 및 공백 제거
     * 
     * @author nhkim8548
     * @version $Id$
     * @since   1.0
     * 
     * @param data
     * @return
     */
    public static String getRowDateToNum(String data)
    {
        String clean = data.replaceAll("[^0-9]", "");
        
        return clean;
    }
    /**
     * '숫자', '-', '.' 을 제외한 특수문자 및 공백 제거 
     * 
     * @author nhkim8548
     * @version $Id$
     * @since   1.0
     * 
     * @param data
     * @return
     */
    public static String getRowMoneyToNum(String data)
    {
        String clean = data.replaceAll("[^-0-9.]", "");
        
        return clean;
    }
    
    /**
     * 해당 Company의 Package No 조회
     * @author  HN4741
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @return
     */
    public static String getPackageNo(String compNo)
    {
        List<Map> cmpList = MwareConfig.getCompanies();
        String _pacNo = "";
        for(Map comMap : cmpList)
        {
            _pacNo = String.valueOf(comMap.get("package_no"));
        }
        
//        if("".equals(_pacNo)) _pacNo = "dream";
        
        return _pacNo;
    }

    public static Object makeDetailFromList(List list, Object obj) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, InstantiationException
    {
        if(list.isEmpty() || list.size() == 0 || list.size() > 1) return obj;  
        
        return CommonUtil.makeDTO((Map)list.get(0), obj.getClass());
    }
    
    /**
     * 리스트에서 변경이 필요한 full_desc를 찾아서 변경후 DTO의 리스트로 리턴
     * 
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param list                  전체 리스트
     * @param rootKey               변경이 시작된 row의 key값
     * @param keyColumnName         key값이 들어있는 컬럼명
     * @param pKeyColumnName        부모의 key값이 들어있는 컬럼명
     * @param descColumnName        desc 컬럼명
     * @param fullDescColumnName    full_desc 컬럼명
     * @param className             리턴 될 DTO의 클래스
     * @return resultList           full_desc가 변경된 DTO 리스트
     * @throws Exception
     */
    public static List makeFullDesc(List<Map> list, String rootKey, String keyColumnName, String pKeyColumnName, String descColumnName, String fullDescColumnName, Class className) throws Exception
    {
        //params are not null
        if(list==null) throw new NullPointerException("list is not null.");
        if(rootKey==null) throw new NullPointerException("rootKey is not null.");
        if(keyColumnName==null) throw new NullPointerException("keyColumnName is not null.");
        if(pKeyColumnName==null) throw new NullPointerException("pKeyColumnName is not null.");
        if(descColumnName==null) throw new NullPointerException("descColumnName is not null.");
        if(fullDescColumnName==null) throw new NullPointerException("fullDescColumnName is not null.");
        
        //fullDesc 구분자
        final String seperator = "-";
        
        //메서드가 완료된 후 해당 리스트를 리턴.
        List resultList = new ArrayList();
        
        int ind = -1;//변경이 시작되는 root의 인덱스 초기화
        List<Integer> pInds = new ArrayList<Integer>();//부모를 탐색하면서 해당 index를 여기에 기록
        String keyCursor = rootKey;
        //탐색하면서 만들어지는 fullDesc는 여기에 저장,삭제되면서 진행된다
        LinkedList<String> fullDesc = new LinkedList<String>(){
            StringBuffer sb = new StringBuffer();
            @Override
            public String toString()
            {
                sb.setLength(0);
                for(int i=this.size()-1;i>=0;i--){
                    if(sb.length()!=0) sb.append(seperator);
                    sb.append(this.get(i));
                }
                return sb.toString();
            }
        };
        
        boolean next = true;
        //root의 부모를 순차적으로 탐색하면서 root의 fullDesc를 만든다
        while(next) { // 더 이상 부모 id를 찾을 수 없을떄까지
            next = false;
            for(int i=0; i<list.size(); i++){
                Map map = list.get(i);
                String key = StringUtil.valueOf(map.get(keyColumnName));
                if("".equals(key)){
                    throw new IOException("keyColumnName is wrong. "+keyColumnName);
                }
                if(key.equals(keyCursor)){
                    //현재 index를 방문했었는지 확인 후 방문 기록을 남긴다.(재방문 시 에러이므로 예외처리)
                    if(pInds.contains(i)) throw new Exception("data is wrong. infinite loof.");
                    pInds.add(i);
                    
                    if(ind<0) ind=i;//변경이 시작되는 root의 인덱스를 저장해 둔다.
                    else fullDesc.offer(StringUtil.valueOf(map.get(descColumnName)));//root가 아닌것만 fullDesc에 쌓는다(부모의 fullDesc를 만듬)
                    
                    keyCursor = StringUtil.valueOf(map.get(pKeyColumnName));
                    //부모를 찾았으니까 다음 부모 탐색을 계속한다
                    next = true;
                    break;
                }
            }
        }
        
        //저장해둔 인덱스로 변경이 시작되는 root를 찾는다
        Map rootItem = new HashMap();
        if(ind>=0) {
            rootItem = list.get(ind);
            list.remove(ind);
        }
        
        //nodes : 탐색 node, depth : 탐색 node의 tree depth
        Stack<Map> nodes = new Stack<Map>();
        Stack<Integer> depth = new Stack<Integer>();
        nodes.push(rootItem);
        depth.push(1);
        
        //root로부터 자식을 탐색(stack)
        Map currentNode;
        String currentKey = rootKey;
        int currentDepth = 1;
        int preDepth = 0;
        int gap = 0;
        while(!nodes.isEmpty())
        {
            currentNode = nodes.pop();
            currentDepth = depth.pop();
            gap = preDepth-currentDepth;
            
            if(currentNode.isEmpty()){//rootKey에 해당하는 row가 list에 없을 때
                currentKey = rootKey;
            }
            else{
                currentKey = StringUtil.valueOf(currentNode.get(keyColumnName));
                for(int i=0;i<=gap;i++){
                    fullDesc.pop();
                }
                fullDesc.push(StringUtil.valueOf(currentNode.get(descColumnName)));
                currentNode.put(fullDescColumnName, fullDesc.toString());
                resultList.add(makeDTO(currentNode, className));
            }
            
            for(Iterator<Map> it=list.iterator(); it.hasNext(); ){
                Map item=it.next();
                if(currentKey.equals(StringUtil.valueOf(item.get(pKeyColumnName)))){
                    it.remove();
                    nodes.push(item);
                    depth.push(currentDepth+1);
                }
            }
            preDepth = currentDepth;
        }
        
        return resultList;
    }
    
    /**
     * Null 값 체크
     * @author  nhkim8548
     * @since   1.0
     * 
     * @param 	id
     * @return
     */
    public static boolean isNullCheck(Object id) 
    {
    	if("null".equals(id) || "".equals(id) || id == null){
     		return true;
     	} else {
     		return false;
     	}
    }
    
    public static boolean isNullCheck(String id) 
    {
        if("null".equals(id) || "".equals(id) || id == null){
            return true;
        } else {
            return false;
        }
    }
    
    public static String getConsultMenuId(HttpServletRequest request, String fileName)
    {
        String menuId = request.getParameter("menuId");

        if(menuId == "" || menuId == null)
        {
            Hashtable menuPathTable = ConsultConfig.getMenuPathTable();
            Iterator<Map.Entry<String, String[]>> it = menuPathTable.entrySet().iterator();

            while (it.hasNext()) {
              Map.Entry<String, String[]> entry = it.next();

              String[] map = (String[])entry.getValue();
              if(fileName.equals(map[2]))
              {
                  menuId = entry.getKey();
              }
            }
        }
        
        return menuId;
    }
    
    public static String getMenuId(HttpServletRequest request, String fileName)
    {
        String menuId = request.getParameter("menuId");

        if(menuId == "" || menuId == null)
        {
            Hashtable menuPathTable = MwareConfig.getMenuPathTable();
            Iterator<Map.Entry<String, String[]>> it = menuPathTable.entrySet().iterator();

            while (it.hasNext()) {
              Map.Entry<String, String[]> entry = it.next();

              String[] map = (String[])entry.getValue();
              if(fileName.equals(map[5]))
              {
                  menuId = entry.getKey();
              }
            }
        }
        
        return menuId;
    }
}
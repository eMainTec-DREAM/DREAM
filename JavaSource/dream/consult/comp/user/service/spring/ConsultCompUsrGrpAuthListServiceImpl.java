package dream.consult.comp.user.service.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import common.bean.User;
import common.util.CommonUtil;
import dream.consult.comp.user.dao.ConsultCompUsrGrpAuthListDAO;
import dream.consult.comp.user.dto.ConsultCompUsrGrpCommonDTO;
import dream.consult.comp.user.service.ConsultCompUsrGrpAuthListService;


/**
 * 권한그룹 - 목록 serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="consultCompUsrGrpAuthListServiceTarget"
 * @spring.txbn id="consultCompUsrGrpAuthListService"
 * @spring.property name="consultCompUsrGrpAuthListDAO" ref="consultCompUsrGrpAuthListDAO"
 */
public class ConsultCompUsrGrpAuthListServiceImpl implements ConsultCompUsrGrpAuthListService
{
    private ConsultCompUsrGrpAuthListDAO consultCompUsrGrpAuthListDAO = null;
    
    private static int cntNum = 1;

    public ConsultCompUsrGrpAuthListDAO getConsultCompUsrGrpAuthListDAO() 
    {
		return consultCompUsrGrpAuthListDAO;
	}

	public void setConsultCompUsrGrpAuthListDAO(ConsultCompUsrGrpAuthListDAO consultCompUsrGrpAuthListDAO) 
	{
		this.consultCompUsrGrpAuthListDAO = consultCompUsrGrpAuthListDAO;
	}

	public List findUseGrpAuthList(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO, User user)
    {   
		String userGroup  = consultCompUsrGrpCommonDTO.getUsrGrpNo();
		boolean admin = false;
		
		String[] adminGrpArray = getAdminUsrGrp(user.getCompNo());
		for(String adminGrp : adminGrpArray)
		{
			if(adminGrp.equals(userGroup))
			{
				admin = true;
			}
		}
	    //메뉴 리스트
        List list       = consultCompUsrGrpAuthListDAO.findUsrGrpAuthList(consultCompUsrGrpCommonDTO, admin);
//        //페이지 리스트
//        List pageList   = consultCompUsrGrpAuthListDAO.findUsrGrpAuthPageList(consultCompUsrGrpCommonDTO);
//        //버튼 리스트
//        List buttonList = consultCompUsrGrpAuthListDAO.findUsrGrpAuthButtonList(consultCompUsrGrpCommonDTO);
        
//        List noMenuPageList = consultCompUsrGrpAuthListDAO.findNoMenuUsrGrpAuthPageList(consultCompUsrGrpCommonDTO);
        
        List pageList = null;
        List buttonList = null;
        
        List resultList = new ArrayList();
        ArrayList<Map> newResult = new ArrayList<Map>();
        newResult.addAll(list);
        
        if(list != null)
        for(Object resultMap : list)
        {
            Map resultM = (Map)resultMap;
            
            
            String menuId = String.valueOf(resultM.get("CODE"));
            String pMenuId = String.valueOf(resultM.get("PCODE"));
            String minLvl = String.valueOf(resultM.get("MINLVL"));
            String curLvl = String.valueOf(resultM.get("LVL"));
            
            if(curLvl.equals(minLvl))
            {
            	newResult.remove(resultM);
            	
                List subList = getSubList(menuId, newResult, "PCODE", "CODE", pageList, buttonList, "M");
                if(subList.size() > 0) resultM.put("rows", subList);

                resultM.put("ID", getCurrentDateTimeMS());
                
                resultList.add(resultM);
            }
            else newResult.remove(resultM);
            
        }
        
        /*if(noMenuPageList != null)
            for(Object resultMap : noMenuPageList)
            {
                Map resultM = (Map)resultMap;
                
                String pageId = String.valueOf(resultM.get("CODE"));
                String minLvl = String.valueOf(resultM.get("MINLVL"));
                String curLvl = String.valueOf(resultM.get("LVL"));
                
                if(curLvl.equals(minLvl))
                {
                    List subList = getPageList(pageId, pageList, "PCODE", "CODE", buttonList);
                    
                    //페이지가 있다면 버튼도 있다! 버튼도 찾자!
                    List btnList = getBtnList(pageId, buttonList, "PCODE", "CODE");
                    
                    if(btnList.size() > 0) subList.addAll(btnList);
                    if(subList.size() > 0) resultM.put("rows", subList);

                    resultM.put("ID", getCurrentDateTimeMS());
                    
                    resultList.add(resultM);
                }
                
            }*/
        
        
        return resultList;
    }
	
	public List getSubList(String pCode, List resultList, String pCodeCol, String codeCol, List pageList, List buttonList, String type)
    {
        List subList = new ArrayList(); //
        ArrayList<Map> newResult = new ArrayList<Map>();
        Map rMap = null; 
        
        newResult.addAll(resultList);
        //현재 최상위 코드(pCode)를 부모로 하는 리스트 찾기
        for(Object object : resultList)
        {
            rMap = (Map)object;
            //상위 Code값이 같은 List를 모으자
            if(String.valueOf(rMap.get(pCodeCol)).equals(pCode))
            {
            	newResult.remove(rMap);
            	
                String _pCode = String.valueOf(rMap.get(codeCol));
                
                //그 List 코드값이 부모 코드인 다른 리스트를 찾아보자
                List list = getSubList(_pCode, newResult, pCodeCol, codeCol, pageList, buttonList, type);
                /*List pagelist = null;
                List btnList = null;
                
                String extCode = String.valueOf(rMap.get("EXTCODE"));
                //이 리스트(메뉴)에 페이지가 연결되어 있다면 ... 페이지와 그 페이지를 Parent로 하는 List (페이지들을 가져오자)
                if(!extCode.equals("")) //extCode : pageId
                {
                    pagelist = getPageList(extCode, pageList, "PCODE", "CODE", buttonList);
                    
                    //페이지가 있다면 버튼도 있다! 버튼도 찾자!
                    btnList = getBtnList(extCode, buttonList, "PCODE", "CODE");
                    
                    buttonList.removeAll(btnList);
                }

                if(btnList != null) list.addAll(btnList);
                if(pagelist != null) list.addAll(pagelist);*/
                
                
                String uniqueId = getCurrentDateTimeMS();

                rMap.put("ID", uniqueId);
                rMap.put("rows", list);
                
                subList.add(rMap);
            }
        }
        return subList;
    }

	/**
	 * 현재페이지의 버튼만 검색
	 * @author  Mark
	 * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
	 * @since   1.0
	 * 
	 * @param pCode
	 * @param buttonList
	 * @param pCodeCol
	 * @param codeCol
	 * @return
	 */
	private List getBtnList(String curPageId, List buttonList, String pCodeCol, String codeCol)
    {
	    List subList = new ArrayList();
        Map rMap = null;
        Map tempMap = null;
        int i = 0;
        //Parent Equip Location Code 가 0인 Equipment를 찾아주세요.
        for(Object object : buttonList)
        {
            rMap = (Map)object;
            tempMap = new HashMap();
        
            String pageId = String.valueOf(rMap.get(pCodeCol));
            if(curPageId.equals(pageId))
            {
                rMap.put("ID", getCurrentDateTimeMS());

                tempMap.putAll(rMap);
                subList.add(tempMap);
            }
        }
        
        return subList;
    }

    public List getPageList(String pCode, List resultList, String pCodeCol, String codeCol, List buttonList)
	{
		List subList = new ArrayList();
		
		ArrayList<Map> newResult = new ArrayList<Map>();
		newResult.addAll(resultList);
		
        Map rMap = null;
        Map tempMap = null;
        int i = 0;
        //Parent Equip Location Code 가 0인 Equipment를 찾아주세요.
        for(Object object : resultList)
        {

            rMap = (Map)object;
            tempMap = new HashMap();
            
            String parentCode = String.valueOf(rMap.get(pCodeCol));
            if(pCode.equals(parentCode))
            {
                String eqLocId = String.valueOf(rMap.get(codeCol));

                rMap.put("ID", getCurrentDateTimeMS());
                //그 List 내부에 가진 code 값이 pCode인 다른 리스트를 찾아보자
                newResult.remove(rMap);
                List subPageList = getPageList(eqLocId, newResult, pCodeCol, codeCol, buttonList);
                List btnList = getBtnList(eqLocId, buttonList, "PCODE", "CODE");
                
                buttonList.removeAll(btnList);
                
                btnList.addAll(subPageList);
                
                rMap.put("rows", btnList);
                tempMap.putAll(rMap);

                subList.add(tempMap);
            }
            

        }
        
        return subList;
	}
	
    public synchronized String getCurrentDateTimeMS() {
    	cntNum++;
        return cntNum+"";
    }
	
	
    public int deleteList(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO, String[] keyNoArr, String[] keyTypeArr, String[] stateArr) throws Exception
    {
        int result = 0;

        if(!keyNoArr.equals(null))
            for(int i = 0; keyNoArr.length > i ; i++)
            {   
                String[] keyArr = keyNoArr[i].split("\\^\\|\\^");
                
                String pageId = keyArr[1];
                String keyNo  = keyArr[0];

                if(keyTypeArr[i].equals("MENU"))
                {
                    //메뉴 권한 추가
                    if(stateArr[i].equals("true"))
                    {
                    	int cntNum = consultCompUsrGrpAuthListDAO.chkMenuAuth(keyNo, consultCompUsrGrpCommonDTO);
                        if(cntNum == 0 )consultCompUsrGrpAuthListDAO.insertMenuAuth(keyNo, consultCompUsrGrpCommonDTO);
                        if(!pageId.equals("NOPAGE"))
                        {
                        	int pgNum = consultCompUsrGrpAuthListDAO.chkPageAuth(pageId, consultCompUsrGrpCommonDTO);
                        	if(pgNum == 0 )consultCompUsrGrpAuthListDAO.insertPageAuth(pageId, consultCompUsrGrpCommonDTO);
                        }
                    }
                    else
                    {
                        //메뉴권한 삭제
                        consultCompUsrGrpAuthListDAO.deleteMenuAuth(keyNo, consultCompUsrGrpCommonDTO);
                        
                        if(!pageId.equals("NOPAGE"))
                            consultCompUsrGrpAuthListDAO.deletePageAuth(pageId, consultCompUsrGrpCommonDTO);
                    }
                }
                else if(keyTypeArr[i].equals("TAB"))
                {
                    //텝 페이지 권한 추가
                    if(stateArr[i].equals("true"))
                    {
                    	int cntNum = consultCompUsrGrpAuthListDAO.chkTabAuth(keyNo, consultCompUsrGrpCommonDTO);
                    	if(cntNum == 0 )consultCompUsrGrpAuthListDAO.insertTabAuth(keyNo, consultCompUsrGrpCommonDTO);
                        if(!pageId.equals("NOPAGE"))
                        {
                        	int pgNum = consultCompUsrGrpAuthListDAO.chkPageAuth(pageId, consultCompUsrGrpCommonDTO);
                        	if(pgNum == 0 ) consultCompUsrGrpAuthListDAO.insertPageAuth(pageId, consultCompUsrGrpCommonDTO);
                        }
                    }
                    else
                    {
                        //텝 페이지 권한 삭제
                        consultCompUsrGrpAuthListDAO.deleteTabAuth(keyNo, consultCompUsrGrpCommonDTO);
                        if(!pageId.equals("NOPAGE"))
                            consultCompUsrGrpAuthListDAO.deletePageAuth(pageId, consultCompUsrGrpCommonDTO);
                    }
                }
                else if(keyTypeArr[i].equals("BUTTON"))
                {
                    //버튼 권한 추가
                    if(stateArr[i].equals("true"))
                    {
                    	int cntNum = consultCompUsrGrpAuthListDAO.chkBtnAuth(keyNo, consultCompUsrGrpCommonDTO);
                    	if(cntNum == 0 )consultCompUsrGrpAuthListDAO.insertBtnAuth(keyNo, consultCompUsrGrpCommonDTO);
                    }
                    else
                    {
                        //텝 페이지 권한 삭제
                        consultCompUsrGrpAuthListDAO.deleteBtnAuth(keyNo, consultCompUsrGrpCommonDTO);
                    }
                }
                else if(keyTypeArr[i].equals("PAGE"))
                {
                    //페이지 권한 추가
                    if(stateArr[i].equals("true"))
                    {
                    	int pgNum = consultCompUsrGrpAuthListDAO.chkPageAuth(pageId, consultCompUsrGrpCommonDTO);
                    	if(pgNum == 0 ) consultCompUsrGrpAuthListDAO.insertPageAuth(pageId, consultCompUsrGrpCommonDTO);
                    }
                    else
                    {
                        //페이지 권한 삭제
                        consultCompUsrGrpAuthListDAO.deletePageAuth(pageId, consultCompUsrGrpCommonDTO);
                    }
                }
            }
        
        return result;
    }

	public void authMenu(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO, User user) throws JSONException {
		JSONObject jsonObj = new JSONObject(consultCompUsrGrpCommonDTO.getJsonArray());
        Iterator i = jsonObj.keys();

        while(i.hasNext())
        {
	        String menuId = i.next().toString();
	        
//	        List<Map<String,String>> mPageList = consultCompUsrGrpAuthListDAO.getMpageList(menuId);
//	        
//	        String pId;
//	        for(Map<String,String> m : mPageList)
//	        {
//	        	pId = String.valueOf(m.get("PAGEID"));
//	        	
//	        	//ALL BUTTON 권한 삭제
//	        	consultCompUsrGrpAuthListDAO.deleteAllPageBtnAuth(pId, consultCompUsrGrpCommonDTO);
//		        //해당 메뉴 ID의 ALL TAB 권한 삭제
//		        consultCompUsrGrpAuthListDAO.deleteAllPageTabAuth("", consultCompUsrGrpCommonDTO);
//		        //해당 메뉴 ID의 ALL PAGE 권한 삭제
//		        consultCompUsrGrpAuthListDAO.deleteAllPageAuth(pId, consultCompUsrGrpCommonDTO);
//	        }

	        //해당 메뉴 ID의  PAGE 권한 삭제
	        consultCompUsrGrpAuthListDAO.deleteAllPageAuth(menuId, consultCompUsrGrpCommonDTO);
	        //해당 메뉴 ID의  MENU 권한 삭제 
	        consultCompUsrGrpAuthListDAO.delteAllMenuAuth(menuId, consultCompUsrGrpCommonDTO);
	        
	        if("1".equals(jsonObj.getString(menuId)))
	        {
//	        	//해당 메뉴ID의 ALL BUTTON 권한 부여 
//		        consultCompUsrGrpAuthListDAO.grantAllBtnAuth(menuId, consultCompUsrGrpCommonDTO);
//		        //해당 메뉴 ID의 ALL TAB 권한 부여
//		        consultCompUsrGrpAuthListDAO.grantAllTabAuth(menuId, consultCompUsrGrpCommonDTO);
		        //해당 메뉴 ID의  PAGE 권한 부여
		        consultCompUsrGrpAuthListDAO.grantAllPageAuth(menuId, consultCompUsrGrpCommonDTO);
		        //해당 메뉴 ID의  MENU 권한 부여 
		        consultCompUsrGrpAuthListDAO.grantAllMenuAuth(menuId, consultCompUsrGrpCommonDTO);
	        }

        }
		
	}

	public List<Map<String, String>> findUseGrpPageList(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO) {
		
		List list   = consultCompUsrGrpAuthListDAO.findUsrGrpPageList(consultCompUsrGrpCommonDTO);
		list = CommonUtil.makeJson(list);
		
		List resultList = new ArrayList();
//        List clonedList = new ArrayList();
        List subList = null;
        String locId;
        String pLocId;
        String minLvl;
        String curLvl;
        
//        clonedList.addAll(list);
        
        if(list != null)
        for(Object resultMap : list)
        {
            Map resultM = (Map)resultMap;
            
            locId = String.valueOf(resultM.get("CURPATH"));
            pLocId = String.valueOf(resultM.get("PPATH"));
            minLvl = "1";
            curLvl = String.valueOf(resultM.get("LVL"));
            
            if(curLvl.equals(minLvl))
            {
            	//clonedList.remove(resultM);
                subList = getSubList(locId, list, "PPATH", "CURPATH");
                if(subList.size() > 0) resultM.put("rows", subList);

//                resultM.put("id", getCurrentDateTimeMS());
                resultList.add(resultM);
            }
           // else clonedList.remove(resultM);
            
        }
        return resultList;
	}
	
	public List getSubList(String pCode, List resultList, String pCodeCol, String codeCol)
	{
	    List subList = new ArrayList();
	    Map rMap = null;
	    
	    //Parent Equip Location Code 가 0인 Equipment를 찾아주세요.
        for(Object object : resultList)
        {
            rMap = (Map)object;
            if(String.valueOf(rMap.get(pCodeCol)).equals(pCode))
            {
                String eqLocId = String.valueOf(rMap.get(codeCol));
                
                List list = getSubList(eqLocId, resultList, pCodeCol, codeCol);
                if(list.size() > 0) rMap.put("rows", list);

                subList.add(rMap);

            }
        }
        
        return subList;
	}

	@Override
	public void savePageAuthList(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO, User user) throws JSONException {
        JSONArray jsonArr = new JSONArray(consultCompUsrGrpCommonDTO.getJsonArray());
        
        for(int ind=0; ind<jsonArr.length(); ind++){
            JSONObject rowObj = (JSONObject) jsonArr.get(ind);
            String pageId = rowObj.get("pageId")==null?"":rowObj.get("pageId").toString();
            String pgPageId = rowObj.get("pgPageId")==null?"":rowObj.get("pgPageId").toString();
            String check = rowObj.get("check")==null?"":rowObj.get("check").toString();
            
//            //해당 메뉴ID의 ALL BUTTON 권한 삭제 
//            consultCompUsrGrpAuthListDAO.deleteAllPageBtnAuth(pageId, consultCompUsrGrpCommonDTO);
            //해당 메뉴 ID의  TAB 권한 삭제
            if(!"".equals(pgPageId)) consultCompUsrGrpAuthListDAO.deleteAllPageTabAuth(pgPageId, consultCompUsrGrpCommonDTO);
            //해당 메뉴 ID의  PAGE 권한 삭제
            consultCompUsrGrpAuthListDAO.deleteAllPagePageAuth(pageId, consultCompUsrGrpCommonDTO);
            
            if("1".equals(check))
            {
//                //해당 메뉴ID의 ALL BUTTON 권한 부여 
//                consultCompUsrGrpAuthListDAO.grantAllPageBtnAuth(pageId, consultCompUsrGrpCommonDTO);
                //해당 메뉴 ID의  TAB 권한 부여 
                if(!"".equals(pgPageId)) consultCompUsrGrpAuthListDAO.grantAllPageTabAuth(pgPageId, consultCompUsrGrpCommonDTO);
                //해당 메뉴 ID의  PAGE 권한 부여
                consultCompUsrGrpAuthListDAO.grantAllPagePageAuth(pageId, consultCompUsrGrpCommonDTO);
                
            }
        }
	}

	@Override
	public void saveBtnAuthList(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO, User user) throws JSONException {
		JSONObject jsonObj = new JSONObject(consultCompUsrGrpCommonDTO.getJsonArray());
        Iterator i = jsonObj.keys();

        while(i.hasNext())
        {
	        String pgbtnId = i.next().toString();

	        //해당 메뉴ID의 ALL BUTTON 권한 삭제 
	        consultCompUsrGrpAuthListDAO.deletePageBtnAuth(pgbtnId, consultCompUsrGrpCommonDTO);
	        
	        if("1".equals(jsonObj.getString(pgbtnId)))
	        {
	        	//해당 메뉴ID의 ALL BUTTON 권한 부여 
		        consultCompUsrGrpAuthListDAO.grantPageBtnAuth(pgbtnId, consultCompUsrGrpCommonDTO);
		        
	        }

        }
	}

	@Override
	public List findUseGrpBtnList(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO) {
		// TODO Auto-generated method stub
		return consultCompUsrGrpAuthListDAO.findUsrGrpBtnList(consultCompUsrGrpCommonDTO);
	}

	@Override
	public String[] getAdminUsrGrp(String compNo) {
		return consultCompUsrGrpAuthListDAO.getAdminUsrGrp(compNo);
	}
}


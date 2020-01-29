package dream.mgr.usrgrp.service.spring;

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
import dream.mgr.usrgrp.dao.MaUsrGrpAuthListDAO;
import dream.mgr.usrgrp.dto.MaUsrGrpCommonDTO;
import dream.mgr.usrgrp.service.MaUsrGrpAuthListService;

/**
 * ���ѱ׷� - ��� serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="maUsrGrpAuthListServiceTarget"
 * @spring.txbn id="maUsrGrpAuthListService"
 * @spring.property name="maUsrGrpAuthListDAO" ref="maUsrGrpAuthListDAO"
 */
public class MaUsrGrpAuthListServiceImpl implements MaUsrGrpAuthListService
{
    private MaUsrGrpAuthListDAO maUsrGrpAuthListDAO = null;
    
    private static int cntNum = 1;

    public MaUsrGrpAuthListDAO getMaUsrGrpAuthListDAO() 
    {
		return maUsrGrpAuthListDAO;
	}

	public void setMaUsrGrpAuthListDAO(MaUsrGrpAuthListDAO maUsrGrpAuthListDAO) 
	{
		this.maUsrGrpAuthListDAO = maUsrGrpAuthListDAO;
	}

	public List findUseGrpAuthList(MaUsrGrpCommonDTO maUsrGrpCommonDTO, User user)
    {   
		String userGroup  = maUsrGrpCommonDTO.getUsrGrpNo();
		boolean admin = false;
		
		String[] adminGrpArray = getAdminUsrGrp(user.getCompNo());
		for(String adminGrp : adminGrpArray)
		{
			if(adminGrp.equals(userGroup))
			{
				admin = true;
			}
		}
	    //�޴� ����Ʈ
        List list       = maUsrGrpAuthListDAO.findUsrGrpAuthList(maUsrGrpCommonDTO, admin);
//        //������ ����Ʈ
//        List pageList   = maUsrGrpAuthListDAO.findUsrGrpAuthPageList(maUsrGrpCommonDTO);
//        //��ư ����Ʈ
//        List buttonList = maUsrGrpAuthListDAO.findUsrGrpAuthButtonList(maUsrGrpCommonDTO);
        
//        List noMenuPageList = maUsrGrpAuthListDAO.findNoMenuUsrGrpAuthPageList(maUsrGrpCommonDTO);
        
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
                    
                    //�������� �ִٸ� ��ư�� �ִ�! ��ư�� ã��!
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
        //���� �ֻ��� �ڵ�(pCode)�� �θ�� �ϴ� ����Ʈ ã��
        for(Object object : resultList)
        {
            rMap = (Map)object;
            //���� Code���� ���� List�� ������
            if(String.valueOf(rMap.get(pCodeCol)).equals(pCode))
            {
            	newResult.remove(rMap);
            	
                String _pCode = String.valueOf(rMap.get(codeCol));
                
                //�� List �ڵ尪�� �θ� �ڵ��� �ٸ� ����Ʈ�� ã�ƺ���
                List list = getSubList(_pCode, newResult, pCodeCol, codeCol, pageList, buttonList, type);
                /*List pagelist = null;
                List btnList = null;
                
                String extCode = String.valueOf(rMap.get("EXTCODE"));
                //�� ����Ʈ(�޴�)�� �������� ����Ǿ� �ִٸ� ... �������� �� �������� Parent�� �ϴ� List (���������� ��������)
                if(!extCode.equals("")) //extCode : pageId
                {
                    pagelist = getPageList(extCode, pageList, "PCODE", "CODE", buttonList);
                    
                    //�������� �ִٸ� ��ư�� �ִ�! ��ư�� ã��!
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
	 * ������������ ��ư�� �˻�
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
        //Parent Equip Location Code �� 0�� Equipment�� ã���ּ���.
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
        //Parent Equip Location Code �� 0�� Equipment�� ã���ּ���.
        for(Object object : resultList)
        {

            rMap = (Map)object;
            tempMap = new HashMap();
            
            String parentCode = String.valueOf(rMap.get(pCodeCol));
            if(pCode.equals(parentCode))
            {
                String eqLocId = String.valueOf(rMap.get(codeCol));

                rMap.put("ID", getCurrentDateTimeMS());
                //�� List ���ο� ���� code ���� pCode�� �ٸ� ����Ʈ�� ã�ƺ���
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
	
	
    public int deleteList(MaUsrGrpCommonDTO maUsrGrpCommonDTO, String[] keyNoArr, String[] keyTypeArr, String[] stateArr) throws Exception
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
                    //�޴� ���� �߰�
                    if(stateArr[i].equals("true"))
                    {
                    	int cntNum = maUsrGrpAuthListDAO.chkMenuAuth(keyNo, maUsrGrpCommonDTO);
                        if(cntNum == 0 )maUsrGrpAuthListDAO.insertMenuAuth(keyNo, maUsrGrpCommonDTO);
                        if(!pageId.equals("NOPAGE"))
                        {
                        	int pgNum = maUsrGrpAuthListDAO.chkPageAuth(pageId, maUsrGrpCommonDTO);
                        	if(pgNum == 0 )maUsrGrpAuthListDAO.insertPageAuth(pageId, maUsrGrpCommonDTO);
                        }
                    }
                    else
                    {
                        //�޴����� ����
                        maUsrGrpAuthListDAO.deleteMenuAuth(keyNo, maUsrGrpCommonDTO);
                        
                        if(!pageId.equals("NOPAGE"))
                            maUsrGrpAuthListDAO.deletePageAuth(pageId, maUsrGrpCommonDTO);
                    }
                }
                else if(keyTypeArr[i].equals("TAB"))
                {
                    //�� ������ ���� �߰�
                    if(stateArr[i].equals("true"))
                    {
                    	int cntNum = maUsrGrpAuthListDAO.chkTabAuth(keyNo, maUsrGrpCommonDTO);
                    	if(cntNum == 0 )maUsrGrpAuthListDAO.insertTabAuth(keyNo, maUsrGrpCommonDTO);
                        if(!pageId.equals("NOPAGE"))
                        {
                        	int pgNum = maUsrGrpAuthListDAO.chkPageAuth(pageId, maUsrGrpCommonDTO);
                        	if(pgNum == 0 ) maUsrGrpAuthListDAO.insertPageAuth(pageId, maUsrGrpCommonDTO);
                        }
                    }
                    else
                    {
                        //�� ������ ���� ����
                        maUsrGrpAuthListDAO.deleteTabAuth(keyNo, maUsrGrpCommonDTO);
                        if(!pageId.equals("NOPAGE"))
                            maUsrGrpAuthListDAO.deletePageAuth(pageId, maUsrGrpCommonDTO);
                    }
                }
                else if(keyTypeArr[i].equals("BUTTON"))
                {
                    //��ư ���� �߰�
                    if(stateArr[i].equals("true"))
                    {
                    	int cntNum = maUsrGrpAuthListDAO.chkBtnAuth(keyNo, maUsrGrpCommonDTO);
                    	if(cntNum == 0 )maUsrGrpAuthListDAO.insertBtnAuth(keyNo, maUsrGrpCommonDTO);
                    }
                    else
                    {
                        //�� ������ ���� ����
                        maUsrGrpAuthListDAO.deleteBtnAuth(keyNo, maUsrGrpCommonDTO);
                    }
                }
                else if(keyTypeArr[i].equals("PAGE"))
                {
                    //������ ���� �߰�
                    if(stateArr[i].equals("true"))
                    {
                    	int pgNum = maUsrGrpAuthListDAO.chkPageAuth(pageId, maUsrGrpCommonDTO);
                    	if(pgNum == 0 ) maUsrGrpAuthListDAO.insertPageAuth(pageId, maUsrGrpCommonDTO);
                    }
                    else
                    {
                        //������ ���� ����
                        maUsrGrpAuthListDAO.deletePageAuth(pageId, maUsrGrpCommonDTO);
                    }
                }
            }
        
        return result;
    }

	public void authMenu(MaUsrGrpCommonDTO maUsrGrpCommonDTO, User user) throws JSONException {
		JSONObject jsonObj = new JSONObject(maUsrGrpCommonDTO.getJsonArray());
        Iterator i = jsonObj.keys();

        while(i.hasNext())
        {
	        String menuId = i.next().toString();
	        
//	        List<Map<String,String>> mPageList = maUsrGrpAuthListDAO.getMpageList(menuId);
//	        
//	        String pId;
//	        for(Map<String,String> m : mPageList)
//	        {
//	        	pId = String.valueOf(m.get("PAGEID"));
//	        	
//	        	//ALL BUTTON ���� ����
//	        	maUsrGrpAuthListDAO.deleteAllPageBtnAuth(pId, maUsrGrpCommonDTO);
//		        //�ش� �޴� ID�� ALL TAB ���� ����
//		        maUsrGrpAuthListDAO.deleteAllPageTabAuth("", maUsrGrpCommonDTO);
//		        //�ش� �޴� ID�� ALL PAGE ���� ����
//		        maUsrGrpAuthListDAO.deleteAllPageAuth(pId, maUsrGrpCommonDTO);
//	        }

	        //�ش� �޴� ID��  PAGE ���� ����
	        maUsrGrpAuthListDAO.deleteAllPageAuth(menuId, maUsrGrpCommonDTO);
	        //�ش� �޴� ID��  MENU ���� ���� 
	        maUsrGrpAuthListDAO.delteAllMenuAuth(menuId, maUsrGrpCommonDTO);
	        
	        if("1".equals(jsonObj.getString(menuId)))
	        {
//	        	//�ش� �޴�ID�� ALL BUTTON ���� �ο� 
//		        maUsrGrpAuthListDAO.grantAllBtnAuth(menuId, maUsrGrpCommonDTO);
//		        //�ش� �޴� ID�� ALL TAB ���� �ο�
//		        maUsrGrpAuthListDAO.grantAllTabAuth(menuId, maUsrGrpCommonDTO);
		        //�ش� �޴� ID��  PAGE ���� �ο�
		        maUsrGrpAuthListDAO.grantAllPageAuth(menuId, maUsrGrpCommonDTO);
		        //�ش� �޴� ID��  MENU ���� �ο� 
		        maUsrGrpAuthListDAO.grantAllMenuAuth(menuId, maUsrGrpCommonDTO);
	        }

        }
		
	}

	public List<Map<String, String>> findUseGrpPageList(MaUsrGrpCommonDTO maUsrGrpCommonDTO) {
		
		List list   = maUsrGrpAuthListDAO.findUsrGrpPageList(maUsrGrpCommonDTO);
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
	    
	    //Parent Equip Location Code �� 0�� Equipment�� ã���ּ���.
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
	public void savePageAuthList(MaUsrGrpCommonDTO maUsrGrpCommonDTO, User user) throws JSONException {
        JSONArray jsonArr = new JSONArray(maUsrGrpCommonDTO.getJsonArray());
        
        for(int ind=0; ind<jsonArr.length(); ind++){
            JSONObject rowObj = (JSONObject) jsonArr.get(ind);
            String pageId = rowObj.get("pageId")==null?"":rowObj.get("pageId").toString();
            String pgPageId = rowObj.get("pgPageId")==null?"":rowObj.get("pgPageId").toString();
            String check = rowObj.get("check")==null?"":rowObj.get("check").toString();
            
//            //�ش� �޴�ID�� ALL BUTTON ���� ���� 
//            maUsrGrpAuthListDAO.deleteAllPageBtnAuth(pageId, maUsrGrpCommonDTO);
            //�ش� �޴� ID��  TAB ���� ����
            if(!"".equals(pgPageId)) maUsrGrpAuthListDAO.deleteAllPageTabAuth(pgPageId, maUsrGrpCommonDTO);
            //�ش� �޴� ID��  PAGE ���� ����
            maUsrGrpAuthListDAO.deleteAllPagePageAuth(pageId, maUsrGrpCommonDTO);
            
            if("1".equals(check))
            {
//                //�ش� �޴�ID�� ALL BUTTON ���� �ο� 
//                maUsrGrpAuthListDAO.grantAllPageBtnAuth(pageId, maUsrGrpCommonDTO);
                //�ش� �޴� ID��  TAB ���� �ο� 
                if(!"".equals(pgPageId)) maUsrGrpAuthListDAO.grantAllPageTabAuth(pgPageId, maUsrGrpCommonDTO);
                //�ش� �޴� ID��  PAGE ���� �ο�
                maUsrGrpAuthListDAO.grantAllPagePageAuth(pageId, maUsrGrpCommonDTO);
                
            }
        }
	}

	@Override
	public void saveBtnAuthList(MaUsrGrpCommonDTO maUsrGrpCommonDTO, User user) throws JSONException {
		JSONObject jsonObj = new JSONObject(maUsrGrpCommonDTO.getJsonArray());
        Iterator i = jsonObj.keys();

        while(i.hasNext())
        {
	        String pgbtnId = i.next().toString();

	        //�ش� �޴�ID�� ALL BUTTON ���� ���� 
	        maUsrGrpAuthListDAO.deletePageBtnAuth(pgbtnId, maUsrGrpCommonDTO);
	        
	        if("1".equals(jsonObj.getString(pgbtnId)))
	        {
	        	//�ش� �޴�ID�� ALL BUTTON ���� �ο� 
		        maUsrGrpAuthListDAO.grantPageBtnAuth(pgbtnId, maUsrGrpCommonDTO);
		        
	        }

        }
	}

	@Override
	public List findUseGrpBtnList(MaUsrGrpCommonDTO maUsrGrpCommonDTO) {
		// TODO Auto-generated method stub
		return maUsrGrpAuthListDAO.findUsrGrpBtnList(maUsrGrpCommonDTO);
	}

	@Override
	public String[] getAdminUsrGrp(String compNo) {
		return maUsrGrpAuthListDAO.getAdminUsrGrp(compNo);
	}
}


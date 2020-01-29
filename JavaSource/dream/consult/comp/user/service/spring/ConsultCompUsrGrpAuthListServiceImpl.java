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
 * ���ѱ׷� - ��� serviceimpl
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
	    //�޴� ����Ʈ
        List list       = consultCompUsrGrpAuthListDAO.findUsrGrpAuthList(consultCompUsrGrpCommonDTO, admin);
//        //������ ����Ʈ
//        List pageList   = consultCompUsrGrpAuthListDAO.findUsrGrpAuthPageList(consultCompUsrGrpCommonDTO);
//        //��ư ����Ʈ
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
                    //�޴� ���� �߰�
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
                        //�޴����� ����
                        consultCompUsrGrpAuthListDAO.deleteMenuAuth(keyNo, consultCompUsrGrpCommonDTO);
                        
                        if(!pageId.equals("NOPAGE"))
                            consultCompUsrGrpAuthListDAO.deletePageAuth(pageId, consultCompUsrGrpCommonDTO);
                    }
                }
                else if(keyTypeArr[i].equals("TAB"))
                {
                    //�� ������ ���� �߰�
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
                        //�� ������ ���� ����
                        consultCompUsrGrpAuthListDAO.deleteTabAuth(keyNo, consultCompUsrGrpCommonDTO);
                        if(!pageId.equals("NOPAGE"))
                            consultCompUsrGrpAuthListDAO.deletePageAuth(pageId, consultCompUsrGrpCommonDTO);
                    }
                }
                else if(keyTypeArr[i].equals("BUTTON"))
                {
                    //��ư ���� �߰�
                    if(stateArr[i].equals("true"))
                    {
                    	int cntNum = consultCompUsrGrpAuthListDAO.chkBtnAuth(keyNo, consultCompUsrGrpCommonDTO);
                    	if(cntNum == 0 )consultCompUsrGrpAuthListDAO.insertBtnAuth(keyNo, consultCompUsrGrpCommonDTO);
                    }
                    else
                    {
                        //�� ������ ���� ����
                        consultCompUsrGrpAuthListDAO.deleteBtnAuth(keyNo, consultCompUsrGrpCommonDTO);
                    }
                }
                else if(keyTypeArr[i].equals("PAGE"))
                {
                    //������ ���� �߰�
                    if(stateArr[i].equals("true"))
                    {
                    	int pgNum = consultCompUsrGrpAuthListDAO.chkPageAuth(pageId, consultCompUsrGrpCommonDTO);
                    	if(pgNum == 0 ) consultCompUsrGrpAuthListDAO.insertPageAuth(pageId, consultCompUsrGrpCommonDTO);
                    }
                    else
                    {
                        //������ ���� ����
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
//	        	//ALL BUTTON ���� ����
//	        	consultCompUsrGrpAuthListDAO.deleteAllPageBtnAuth(pId, consultCompUsrGrpCommonDTO);
//		        //�ش� �޴� ID�� ALL TAB ���� ����
//		        consultCompUsrGrpAuthListDAO.deleteAllPageTabAuth("", consultCompUsrGrpCommonDTO);
//		        //�ش� �޴� ID�� ALL PAGE ���� ����
//		        consultCompUsrGrpAuthListDAO.deleteAllPageAuth(pId, consultCompUsrGrpCommonDTO);
//	        }

	        //�ش� �޴� ID��  PAGE ���� ����
	        consultCompUsrGrpAuthListDAO.deleteAllPageAuth(menuId, consultCompUsrGrpCommonDTO);
	        //�ش� �޴� ID��  MENU ���� ���� 
	        consultCompUsrGrpAuthListDAO.delteAllMenuAuth(menuId, consultCompUsrGrpCommonDTO);
	        
	        if("1".equals(jsonObj.getString(menuId)))
	        {
//	        	//�ش� �޴�ID�� ALL BUTTON ���� �ο� 
//		        consultCompUsrGrpAuthListDAO.grantAllBtnAuth(menuId, consultCompUsrGrpCommonDTO);
//		        //�ش� �޴� ID�� ALL TAB ���� �ο�
//		        consultCompUsrGrpAuthListDAO.grantAllTabAuth(menuId, consultCompUsrGrpCommonDTO);
		        //�ش� �޴� ID��  PAGE ���� �ο�
		        consultCompUsrGrpAuthListDAO.grantAllPageAuth(menuId, consultCompUsrGrpCommonDTO);
		        //�ش� �޴� ID��  MENU ���� �ο� 
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
	public void savePageAuthList(ConsultCompUsrGrpCommonDTO consultCompUsrGrpCommonDTO, User user) throws JSONException {
        JSONArray jsonArr = new JSONArray(consultCompUsrGrpCommonDTO.getJsonArray());
        
        for(int ind=0; ind<jsonArr.length(); ind++){
            JSONObject rowObj = (JSONObject) jsonArr.get(ind);
            String pageId = rowObj.get("pageId")==null?"":rowObj.get("pageId").toString();
            String pgPageId = rowObj.get("pgPageId")==null?"":rowObj.get("pgPageId").toString();
            String check = rowObj.get("check")==null?"":rowObj.get("check").toString();
            
//            //�ش� �޴�ID�� ALL BUTTON ���� ���� 
//            consultCompUsrGrpAuthListDAO.deleteAllPageBtnAuth(pageId, consultCompUsrGrpCommonDTO);
            //�ش� �޴� ID��  TAB ���� ����
            if(!"".equals(pgPageId)) consultCompUsrGrpAuthListDAO.deleteAllPageTabAuth(pgPageId, consultCompUsrGrpCommonDTO);
            //�ش� �޴� ID��  PAGE ���� ����
            consultCompUsrGrpAuthListDAO.deleteAllPagePageAuth(pageId, consultCompUsrGrpCommonDTO);
            
            if("1".equals(check))
            {
//                //�ش� �޴�ID�� ALL BUTTON ���� �ο� 
//                consultCompUsrGrpAuthListDAO.grantAllPageBtnAuth(pageId, consultCompUsrGrpCommonDTO);
                //�ش� �޴� ID��  TAB ���� �ο� 
                if(!"".equals(pgPageId)) consultCompUsrGrpAuthListDAO.grantAllPageTabAuth(pgPageId, consultCompUsrGrpCommonDTO);
                //�ش� �޴� ID��  PAGE ���� �ο�
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

	        //�ش� �޴�ID�� ALL BUTTON ���� ���� 
	        consultCompUsrGrpAuthListDAO.deletePageBtnAuth(pgbtnId, consultCompUsrGrpCommonDTO);
	        
	        if("1".equals(jsonObj.getString(pgbtnId)))
	        {
	        	//�ش� �޴�ID�� ALL BUTTON ���� �ο� 
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


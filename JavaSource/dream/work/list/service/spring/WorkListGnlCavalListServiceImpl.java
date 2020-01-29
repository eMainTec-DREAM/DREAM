package dream.work.list.service.spring;

import java.util.List;
import java.util.Map;

import com.fins.org.json.JSONArray;
import com.fins.org.json.JSONObject;

import common.bean.User;
import common.util.CommonUtil;
import dream.work.list.dao.WorkListGnlCavalListDAO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.WorkListGnlCavalDetailDTO;
import dream.work.list.service.WorkListGnlCavalDetailService;
import dream.work.list.service.WorkListGnlCavalListService;

/**
 * 작업상세  - 검교정 - 측정값목록
 * @author kim21017
 * @version $Id: WorkListGnlCavalListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="workListGnlCavalListServiceTarget"
 * @spring.txbn id="workListGnlCavalListService"
 * @spring.property name="workListGnlCavalListDAO" ref="workListGnlCavalListDAO"
 * @spring.property name="workListGnlCavalDetailService" ref="workListGnlCavalDetailService"
 */
public class WorkListGnlCavalListServiceImpl implements WorkListGnlCavalListService
{
    private WorkListGnlCavalListDAO workListGnlCavalListDAO = null;
    
    private WorkListGnlCavalDetailService workListGnlCavalDetailService = null;

    public WorkListGnlCavalDetailService getWorkListGnlCavalDetailService() {
		return workListGnlCavalDetailService;
	}

	public void setWorkListGnlCavalDetailService(WorkListGnlCavalDetailService workListGnlCavalDetailService) {
		this.workListGnlCavalDetailService = workListGnlCavalDetailService;
	}

	public WorkListGnlCavalListDAO getWorkListGnlCavalListDAO() {
		return workListGnlCavalListDAO;
	}

	public void setWorkListGnlCavalListDAO(WorkListGnlCavalListDAO workListGnlCavalListDAO) {
		this.workListGnlCavalListDAO = workListGnlCavalListDAO;
	}

	public List findCavalList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User loginUser)
    {      
        return workListGnlCavalListDAO.findCavalList(maWoResultMstrCommonDTO, loginUser);
    }
	
	public int deleteCavalList(String[] deleteRows, String compNo) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + workListGnlCavalListDAO.deleteCavalList(id, compNo);
            }
        
        return result;
    }
	
	public int copyCavalList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User loginUser) throws Exception{
        int result = 0;
        result = result + workListGnlCavalListDAO.copyCavalList(maWoResultMstrCommonDTO, loginUser);
        return result;
	}
	
	public int batchCavalList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User loginUser, String jsonStr) throws Exception{
		int result = 0;

		JSONArray array = new JSONArray(jsonStr); 
    	if(array!=null && array.length()>0){
    		int listSize = array.length();
    		for(int i=0; i<listSize;i++){
    			JSONObject jsonObject = array.getJSONObject(i);
    			String valId = jsonObject.getString("WOCALIBGNLVALUEID");
    			String calibPoint 	= jsonObject.getString("CALIBPOINT");
    			String allowValue 	= jsonObject.getString("ALLOWVALUE");
    			String asfStdValue 	= jsonObject.getString("ASFSTDVALUE");
    			String asfCalValue 	= jsonObject.getString("ASFCALVALUE");
    			String asfDiffValue = jsonObject.getString("ASFDIFFVALUE");
    			String aslStdValue 	= jsonObject.getString("ASLSTDVALUE");
    			String aslCalValue 	= jsonObject.getString("ASLCALVALUE");
    			String aslDiffValue = jsonObject.getString("ASLDIFFVALUE");
    			
				result = result + workListGnlCavalListDAO.batchCavalList(maWoResultMstrCommonDTO, loginUser, valId
											,calibPoint, allowValue
											,asfStdValue, asfCalValue, asfDiffValue
											,aslStdValue, aslCalValue, aslDiffValue
											);
			}
    	}
		return result;
	}
	

	@Override
	public void saveList(List<Map> gridList, User user) throws Exception 
	{
		WorkListGnlCavalDetailDTO workListGnlCavalDetailDTO = null;
		MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = null;
		
        for(Map map : gridList)
        {
        	workListGnlCavalDetailDTO = (WorkListGnlCavalDetailDTO)CommonUtil.makeDTO(map, WorkListGnlCavalDetailDTO.class);
        	maWoResultMstrCommonDTO = (MaWoResultMstrCommonDTO)CommonUtil.makeDTO(map, MaWoResultMstrCommonDTO.class);

        	maWoResultMstrCommonDTO.setCompNo(user.getCompNo());
        	
        	switch(workListGnlCavalDetailDTO.getNativeeditor())
        	{
        		case "inserted":
        			break;
        		case "updated" : workListGnlCavalDetailService.updateDetail(workListGnlCavalDetailDTO, maWoResultMstrCommonDTO);
        			break;
        		case "deleted": workListGnlCavalListDAO.deleteCavalList(workListGnlCavalDetailDTO.getWocalibgnlvalueId(), user.getCompNo());
        			break;
        	}
        	
        }
	}

	public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser) throws Exception 
	{
		return workListGnlCavalListDAO.findTotalCount(maWoResultMstrCommonDTO, loginUser);		
	}

}


package intf.dream.bee.inspection.service.spring;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultMstrDetailDTO;
import dream.work.list.dto.MaWoResultPmCalDTO;
import dream.work.list.service.MaWoResultMstrDetailService;
import intf.dream.bee.inspection.dao.BeeInspectionListDAO;
import intf.dream.bee.inspection.dto.BeeInspectionCommonDTO;
import intf.dream.bee.inspection.service.BeeInspectionListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeInspectionListServiceTarget"
 * @spring.txbn id="beeInspectionListService"
 * @spring.property name="beeInspectionListDAO" ref="beeInspectionListDAO"
 * @spring.property name="maWoResultMstrDetailService" ref="maWoResultMstrDetailService"
 */
public class BeeInspectionListServiceImpl implements BeeInspectionListService
{
    private BeeInspectionListDAO beeInspectionListDAO = null;
    private MaWoResultMstrDetailService maWoResultMstrDetailService = null;
    
	public MaWoResultMstrDetailService getMaWoResultMstrDetailService() {
		return maWoResultMstrDetailService;
	}
	public void setMaWoResultMstrDetailService(MaWoResultMstrDetailService maWoResultMstrDetailService) {
		this.maWoResultMstrDetailService = maWoResultMstrDetailService;
	}
	public BeeInspectionListDAO getBeeInspectionListDAO() {
		return beeInspectionListDAO;
	}
	public void setBeeInspectionListDAO(BeeInspectionListDAO beeInspectionListDAO) {
		this.beeInspectionListDAO = beeInspectionListDAO;
	}
	
	public List findInspectionList(BeeInspectionCommonDTO beeInspectionCommonDTO, Map map) throws Exception
	{      
		return beeInspectionListDAO.findInspectionList(beeInspectionCommonDTO, map);
	}
	public List findInspectionCount(BeeInspectionCommonDTO beeInspectionCommonDTO, Map map) throws Exception
	{      
		return beeInspectionListDAO.findInspectionCount(beeInspectionCommonDTO, map);
	}
	public List findPointList(Map map) throws Exception
	{      
		return beeInspectionListDAO.findPointList(map);
	}
	public List findPointHistList(Map map) throws Exception
	{      
		return beeInspectionListDAO.findPointHistList(map);
	}
	public List findWoPointCount(Map map) throws Exception
	{      
		return beeInspectionListDAO.findWoPointCount(map);
	}
	public List findStatus(Map map) throws Exception
	{      
		return beeInspectionListDAO.findStatus(map);
	}
	
	public int insertPoint(List list)  throws Exception
	{
		int resultQty = 0;
		MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();

		for(Object obj : list){
			Map map = (Map)obj;
			
			if(!"".equals(CommonUtil.convertString(map.get("resultStatus")))){
				beeInspectionListDAO.insertPoint(map);
				beeInspectionListDAO.updateStartDate(map);
				beeInspectionListDAO.updateEndDate(map);
				
				String userId = CommonUtil.convertString(map.get("userId"));
	            String wkOrId = CommonUtil.convertString(map.get("wkorId"));
	            String compNo = CommonUtil.convertString(map.get("compNo"));
	            
	            if(!"".equals(wkOrId) && "Y".equals(CommonUtil.convertString(map.get("isConfirm"))))
	            {
	                maWoResultMstrCommonDTO.setWkOrId(wkOrId);
	                maWoResultMstrCommonDTO.setCompNo(compNo);
	                MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = maWoResultMstrDetailService.findDetail(maWoResultMstrCommonDTO);
	                MaWoResultPmCalDTO maWoResultPmCalDTO = new MaWoResultPmCalDTO();
	                maWoResultMstrDetailDTO.setCompNo(compNo);
	                maWoResultMstrDetailDTO.setEnterBy(userId);
	                maWoResultMstrDetailDTO.setWkorDate(maWoResultMstrDetailDTO.getWkorDate());
	                maWoResultMstrDetailDTO.setStartDate(maWoResultMstrDetailDTO.getStartDate());
	                maWoResultMstrDetailDTO.setStartTime(maWoResultMstrDetailDTO.getStartTime());
	                maWoResultMstrDetailDTO.setEndDate(maWoResultMstrDetailDTO.getEndDate());
	                maWoResultMstrDetailDTO.setEndTime(maWoResultMstrDetailDTO.getEndTime());

	                User user = new User();
	                user.setCompNo(compNo);
	                user.setUserId(userId);
	                String lang = "".equals(CommonUtil.convertString(map.get("lang")))?"ko":CommonUtil.convertString(map.get("lang"));
	                user.setLangId(lang);
	                Locale locale = new Locale(lang);
	                user.setLocale(locale);
	                maWoResultMstrDetailDTO.setLoginUser(user);
	                
	                //이상점검 insert 
	    			if(!"GD".equals(CommonUtil.convertString(map.get("resultStatus")))){
	    				beeInspectionListDAO.mergeAbnormalRslt(map);
	    			}
	    			
	                //완료가 아니면 완료 시킴
	                if(!"C".equals(maWoResultMstrDetailDTO.getWoStatusId())&&maWoResultMstrDetailDTO.getWkOrId()!=null&&!"".equals(maWoResultMstrDetailDTO.getWkOrId())){
	                	maWoResultMstrDetailService.completeDetail(maWoResultMstrDetailDTO,maWoResultPmCalDTO);
	                }
	            }
				
			}
		}
		return resultQty;
	}
	
	public int updatePoint(List list)  throws Exception
	{
		int resultQty = 0;
		MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();

		for(Object obj : list){
			Map map = (Map)obj;
			
			if(!"".equals(CommonUtil.convertString(map.get("resultStatus")))){
				
				beeInspectionListDAO.updatePoint(map);
				beeInspectionListDAO.updateEndDate(map);
				
				String userId = CommonUtil.convertString(map.get("userId"));
	            String wkOrId = CommonUtil.convertString(map.get("wkorId"));
	            String compNo = CommonUtil.convertString(map.get("compNo"));
	            
	            if(!"".equals(wkOrId) && "Y".equals(CommonUtil.convertString(map.get("isConfirm"))))
	            {
	                maWoResultMstrCommonDTO.setWkOrId(wkOrId);
	                maWoResultMstrCommonDTO.setCompNo(compNo);
	                MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = maWoResultMstrDetailService.findDetail(maWoResultMstrCommonDTO);
	                MaWoResultPmCalDTO maWoResultPmCalDTO = new MaWoResultPmCalDTO();
	                maWoResultMstrDetailDTO.setCompNo(compNo);
	                maWoResultMstrDetailDTO.setEnterBy(userId);
	                maWoResultMstrDetailDTO.setWkorDate(maWoResultMstrDetailDTO.getWkorDate());
	                maWoResultMstrDetailDTO.setStartDate(maWoResultMstrDetailDTO.getStartDate());
	                maWoResultMstrDetailDTO.setStartTime(maWoResultMstrDetailDTO.getStartTime());
	                maWoResultMstrDetailDTO.setEndDate(maWoResultMstrDetailDTO.getEndDate());
	                maWoResultMstrDetailDTO.setEndTime(maWoResultMstrDetailDTO.getEndTime());
	                
	                User user = new User();
	                user.setCompNo(compNo);
	                user.setUserId(userId);
	                String lang = "".equals(CommonUtil.convertString(map.get("lang")))?"ko":CommonUtil.convertString(map.get("lang"));
	                user.setLangId(lang);
	                Locale locale = new Locale(lang);
	                user.setLocale(locale);
	                maWoResultMstrDetailDTO.setLoginUser(user);
	                
	              //이상점검 insert 
	    			if(!"GD".equals(CommonUtil.convertString(map.get("resultStatus")))){
	    				beeInspectionListDAO.mergeAbnormalRslt(map);
	    			}
	    			
	                //완료가 아니면 완료 시킴
	                if(!"C".equals(maWoResultMstrDetailDTO.getWoStatusId())&&maWoResultMstrDetailDTO.getWkOrId()!=null&&!"".equals(maWoResultMstrDetailDTO.getWkOrId())){
	                	maWoResultMstrDetailService.completeDetail(maWoResultMstrDetailDTO,maWoResultPmCalDTO);
	                }
	            }
				
			}
		}
		
		
		return resultQty;
	}
}


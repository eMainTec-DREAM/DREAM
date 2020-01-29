package intf.dream.android.online.inspection.service.spring;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultMstrDetailDTO;
import dream.work.list.dto.MaWoResultPmCalDTO;
import dream.work.list.service.MaWoResultMstrDetailService;
import intf.dream.android.online.inspection.dao.AnOnInspectionListDAO;
import intf.dream.android.online.inspection.service.AnOnInspectionListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnInspectionListServiceTarget"
 * @spring.txbn id="anOnInspectionListService"
 * @spring.property name="anOnInspectionListDAO" ref="anOnInspectionListDAO"
 * @spring.property name="maWoResultMstrDetailService" ref="maWoResultMstrDetailService"
 */
public class AnOnInspectionListServiceImpl implements AnOnInspectionListService
{
    private AnOnInspectionListDAO anOnInspectionListDAO = null;
    private MaWoResultMstrDetailService maWoResultMstrDetailService = null;
    
	public MaWoResultMstrDetailService getMaWoResultMstrDetailService() {
		return maWoResultMstrDetailService;
	}
	public void setMaWoResultMstrDetailService(MaWoResultMstrDetailService maWoResultMstrDetailService) {
		this.maWoResultMstrDetailService = maWoResultMstrDetailService;
	}
	public AnOnInspectionListDAO getAnOnInspectionListDAO() {
		return anOnInspectionListDAO;
	}
	public void setAnOnInspectionListDAO(AnOnInspectionListDAO anOnInspectionListDAO) {
		this.anOnInspectionListDAO = anOnInspectionListDAO;
	}
	
	public List findInspectionList(Map map) throws Exception
	{      
		return anOnInspectionListDAO.findInspectionList(map);
	}
	public List findPointList(Map map) throws Exception
	{      
		return anOnInspectionListDAO.findPointList(map);
	}
	public List findPointHistList(Map map) throws Exception
	{      
		return anOnInspectionListDAO.findPointHistList(map);
	}
	public List findWoPointCount(Map map) throws Exception
	{      
		return anOnInspectionListDAO.findWoPointCount(map);
	}
	public List findStatus(Map map) throws Exception
	{      
		return anOnInspectionListDAO.findStatus(map);
	}
	
	public int insertPoint(List list)  throws Exception
	{
		int resultQty = 0;
		MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();

		for(Object obj : list){
			Map map = (Map)obj;
			
			if(!"".equals(CommonUtil.convertString(map.get("resultStatus")))){
				
				anOnInspectionListDAO.insertPoint(map);
				anOnInspectionListDAO.updateStartDate(map);
				anOnInspectionListDAO.updateEndDate(map);
				
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
	    				anOnInspectionListDAO.mergeAbnormalRslt(map);
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
				
				anOnInspectionListDAO.updatePoint(map);
				anOnInspectionListDAO.updateEndDate(map);
				
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
	    				anOnInspectionListDAO.mergeAbnormalRslt(map);
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


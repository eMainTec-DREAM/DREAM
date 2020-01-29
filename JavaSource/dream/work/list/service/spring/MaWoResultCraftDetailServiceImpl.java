package dream.work.list.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import common.util.DateUtil;
import dream.work.list.dao.MaWoResultCraftDetailDAO;
import dream.work.list.dto.MaWoResultCraftDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultMstrDetailDTO;
import dream.work.list.service.MaWoResultCraftDetailService;
import dream.work.list.service.MaWoResultMstrDetailService;

/**
 * 작업결과 작업자
 * @author kim2107
 * @version $Id: MaWoResultCraftDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maWoResultCraftDetailServiceTarget"
 * @spring.txbn id="maWoResultCraftDetailService"
 * @spring.property name="maWoResultCraftDetailDAO" ref="maWoResultCraftDetailDAO"
 */
public class MaWoResultCraftDetailServiceImpl implements MaWoResultCraftDetailService
{
    private MaWoResultCraftDetailDAO maWoResultCraftDetailDAO = null;
    
    public MaWoResultCraftDetailDAO getMaWoResultCraftDetailDAO() {
		return maWoResultCraftDetailDAO;
	}

	public void setMaWoResultCraftDetailDAO(MaWoResultCraftDetailDAO maWoResultCraftDetailDAO) {
		this.maWoResultCraftDetailDAO = maWoResultCraftDetailDAO;
	}

	public MaWoResultCraftDetailDTO findDetail(String wkOrId, String woCraftId, String compNo)throws Exception
    {
        return maWoResultCraftDetailDAO.findDetail(wkOrId, woCraftId, compNo);
    }
    
	public int updateDetail(MaWoResultCraftDetailDTO maWoResultCraftDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception
    {        
        return maWoResultCraftDetailDAO.updateDetail(maWoResultCraftDetailDTO, maWoResultMstrCommonDTO);
    }
	public int insertDetail(MaWoResultCraftDetailDTO maWoResultCraftDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) throws Exception
    {        
        return maWoResultCraftDetailDAO.insertDetail( maWoResultCraftDetailDTO, maWoResultMstrCommonDTO);
    }
	public String validEmp(MaWoResultCraftDetailDTO maWoResultCraftDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User loginUser){
		return maWoResultCraftDetailDAO.validEmp(maWoResultCraftDetailDTO, maWoResultMstrCommonDTO, loginUser);
	}
	public String validTime(MaWoResultCraftDetailDTO maWoResultCraftDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, User user) throws Exception{
		String rslt = "";
		
		MaWoResultMstrDetailService maWoResultMstrDetailService = (MaWoResultMstrDetailService) CommonUtil.getBean("maWoResultMstrDetailService");
		
		maWoResultMstrCommonDTO.setCompNo(user.getCompNo());
		MaWoResultMstrDetailDTO maWoResultMstrDetailDTO = maWoResultMstrDetailService.findDetail(maWoResultMstrCommonDTO);
		
		String woStartDate = "".equals(maWoResultMstrDetailDTO.getStartDate()) ? DateUtil.getDate() : maWoResultMstrDetailDTO.getStartDate();
		String woEndDate = "".equals(maWoResultMstrDetailDTO.getEndDate()) ? DateUtil.getDate() : maWoResultMstrDetailDTO.getEndDate();
		String craftStartDate = "".equals(maWoResultCraftDetailDTO.getStartDate()) ? DateUtil.getDate() : maWoResultCraftDetailDTO.getStartDate();
		String craftEndDate = "".equals(maWoResultCraftDetailDTO.getEndDate()) ? DateUtil.getDate() : maWoResultCraftDetailDTO.getEndDate();
		String woStartTime = "".equals(maWoResultMstrDetailDTO.getStartTime()) ? "00:00" : maWoResultMstrDetailDTO.getStartTime(); 
		String woEndTime = "".equals(maWoResultMstrDetailDTO.getEndTime()) ? "23:59" : maWoResultMstrDetailDTO.getEndTime();
		String craftStartTime = "".equals(maWoResultCraftDetailDTO.getStartTime()) ? "00:00" : maWoResultCraftDetailDTO.getStartTime();
		String craftEndTime = "".equals(maWoResultCraftDetailDTO.getEndTime()) ? "23:59" : maWoResultCraftDetailDTO.getEndTime();		
		
		// 작업결과서 - 작업시작시간, 작업종료시간
		long woStDateTime = Long.parseLong(CommonUtil.getRowDateToNum(woStartDate + woStartTime));
		long woEdDateTime = Long.parseLong(CommonUtil.getRowDateToNum(woEndDate + woEndTime));
		
		// 작업자 - 작업시작시간, 작업종료시간
		long woCraftStDateTime = Long.parseLong(CommonUtil.getRowDateToNum(craftStartDate + craftStartTime));
		long woCraftEdDateTime = Long.parseLong(CommonUtil.getRowDateToNum(craftEndDate + craftEndTime));
		
		if("PMC".equals(maWoResultMstrDetailDTO.getWoTypeId()) || (woStDateTime <= woCraftStDateTime && woEdDateTime >= woCraftEdDateTime)) {
			List woCraftList = maWoResultCraftDetailDAO.validTime(maWoResultCraftDetailDTO, maWoResultMstrCommonDTO, user);
			
			if(woCraftList.size() != 0){
				for(Object woCraftDetailList: woCraftList) {
					Map result = (Map)woCraftDetailList;
					// DB에 저장되어있는 작업시작시간, 작업종료시간 (수정 중인 작업시작시간, 작업종료시간 제외) 
					long chkStDateTime = Long.parseLong(CommonUtil.getRowDateToNum(String.valueOf(result.get("startDate"))+String.valueOf(result.get("startTime"))));
					long chkEdDateTime = Long.parseLong(CommonUtil.getRowDateToNum(String.valueOf(result.get("endDate"))+String.valueOf(result.get("endTime"))));
					
					if(woCraftStDateTime >= chkStDateTime && woCraftStDateTime <= chkEdDateTime) {
						rslt = "1";
					} else if(woCraftEdDateTime >= chkStDateTime && woCraftEdDateTime <= chkEdDateTime) {
						rslt = "1";
					} else if(chkStDateTime >= woCraftStDateTime && chkStDateTime <=woCraftEdDateTime) {
						rslt = "1";
					} else if(chkEdDateTime >= woCraftStDateTime && chkEdDateTime <= woCraftEdDateTime){
						rslt = "1";
					} else 
						rslt = "0";
				}
			} else {
				rslt = "0";
			}
		} else {
			rslt = "2";
		}
		
		return rslt;
	}
}

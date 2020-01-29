package intf.dream.bee.maptpurreq.service.spring;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import common.util.MailUtil;
import dream.part.pur.req.dto.MaPtPurReqDetailDTO;
import intf.dream.bee.maptpurreq.dao.BeeMaPtPurReqListDAO;
import intf.dream.bee.maptpurreq.dto.BeeMaPtPurReqCommonDTO;
import intf.dream.bee.maptpurreq.service.BeeMaPtPurReqListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="beeMaPtPurReqListServiceTarget"
 * @spring.txbn id="beeMaPtPurReqListService"
 * @spring.property name="beeMaPtPurReqListDAO" ref="beeMaPtPurReqListDAO"
 */
public class BeeMaPtPurReqListServiceImpl implements BeeMaPtPurReqListService
{
    private BeeMaPtPurReqListDAO beeMaPtPurReqListDAO = null;
    
	public BeeMaPtPurReqListDAO getBeeMaPtPurReqListDAO() {
		return beeMaPtPurReqListDAO;
	}
	public void setBeeMaPtPurReqListDAO(BeeMaPtPurReqListDAO beeMaPtPurReqListDAO) {
		this.beeMaPtPurReqListDAO = beeMaPtPurReqListDAO;
	}
	
	public List findMaPtPurReqList(BeeMaPtPurReqCommonDTO beeMaPtPurReqCommonDTO, Map map) throws Exception
	{      
		return beeMaPtPurReqListDAO.findMaPtPurReqList(beeMaPtPurReqCommonDTO, map);
	}
	
	public List findMaPtPurReqCount(BeeMaPtPurReqCommonDTO beeMaPtPurReqCommonDTO, Map map) throws Exception
	{      
		return beeMaPtPurReqListDAO.findMaPtPurReqCount(beeMaPtPurReqCommonDTO, map);
	}
	public int deleteMaPtPurReq(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeMaPtPurReqListDAO.deleteMaPtPurReq(map);
		}
		return resultQty;
	}
	public int insertMaPtPurReq(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeMaPtPurReqListDAO.insertMaPtPurReq(map);
			//beeMaPtPurReqListDAO.insertMaPtPurReqHdr(map);
		}
		return resultQty;
	}
	public int updateMaPtPurReq(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeMaPtPurReqListDAO.updateMaPtPurReq(map);
		}
		return resultQty;
	}
	public int confirmMaPtPurReq(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			beeMaPtPurReqListDAO.confirmMaPtPurReq(map);
			
			MaPtPurReqDetailDTO maPtPurReqDetailDTO = new MaPtPurReqDetailDTO();
			maPtPurReqDetailDTO.setReqId(CommonUtil.convertString(map.get("ptpnlistId")));
			
			User user = new User();
			user.setCompNo(CommonUtil.convertString(map.get("compNo")));
			user.setLangId("".equals(CommonUtil.convertString(map.get("lang")))?"ko":CommonUtil.convertString(map.get("lang")));
			Locale locale = new Locale(user.getLangId());
			user.setLocale(locale);
			
			
			MailUtil.sendMail("PPR10", maPtPurReqDetailDTO.getReqId(), user);
		}
		return resultQty;
	}
}


package intf.dream.android.online.maptpurreq.service.spring;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import common.util.MailUtil;
import dream.part.pur.req.dto.MaPtPurReqDetailDTO;
import dream.req.work.dto.MaWoReqDetailDTO;
import intf.dream.android.online.maptpurreq.dao.AnOnMaPtPurReqListDAO;
import intf.dream.android.online.maptpurreq.service.AnOnMaPtPurReqListService;

/**
 * serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="anOnMaPtPurReqListServiceTarget"
 * @spring.txbn id="anOnMaPtPurReqListService"
 * @spring.property name="anOnMaPtPurReqListDAO" ref="anOnMaPtPurReqListDAO"
 */
public class AnOnMaPtPurReqListServiceImpl implements AnOnMaPtPurReqListService
{
    private AnOnMaPtPurReqListDAO anOnMaPtPurReqListDAO = null;
    
	public AnOnMaPtPurReqListDAO getAnOnMaPtPurReqListDAO() {
		return anOnMaPtPurReqListDAO;
	}
	public void setAnOnMaPtPurReqListDAO(AnOnMaPtPurReqListDAO anOnMaPtPurReqListDAO) {
		this.anOnMaPtPurReqListDAO = anOnMaPtPurReqListDAO;
	}
	
	public List findMaPtPurReqList(Map map) throws Exception
	{      
		return anOnMaPtPurReqListDAO.findMaPtPurReqList(map);
	}
	public int deleteMaPtPurReq(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnMaPtPurReqListDAO.deleteMaPtPurReq(map);
		}
		return resultQty;
	}
	public int insertMaPtPurReq(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnMaPtPurReqListDAO.insertMaPtPurReq(map);
			//anOnMaPtPurReqListDAO.insertMaPtPurReqHdr(map);
		}
		return resultQty;
	}
	public int updateMaPtPurReq(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnMaPtPurReqListDAO.updateMaPtPurReq(map);
		}
		return resultQty;
	}
	public int confirmMaPtPurReq(List list)  throws Exception
	{
		int resultQty = 0;

		for(Object obj : list){
			Map map = (Map)obj;
			anOnMaPtPurReqListDAO.confirmMaPtPurReq(map);
			
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


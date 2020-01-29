package dream.part.pur.buy.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import common.util.StringUtil;
import dream.part.pur.buy.dao.MaPtBuyReqHdrListDAO;
import dream.part.pur.buy.dto.MaPtBuyReqHdrCommonDTO;
import dream.part.pur.buy.dto.MaPtBuyReqListDTO;
import dream.part.pur.buy.service.MaPtBuyReqHdrListService;
import dream.part.pur.buy.service.MaPtBuyReqListService;

/**
 * 구매신청 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: MaPtBuyReqHdrListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maPtBuyReqHdrListServiceTarget"
 * @spring.txbn id="maPtBuyReqHdrListService"
 * @spring.property name="maPtBuyReqHdrListDAO" ref="maPtBuyReqHdrListDAO"
 */
public class MaPtBuyReqHdrListServiceImpl implements MaPtBuyReqHdrListService
{
    private MaPtBuyReqHdrListDAO maPtBuyReqHdrListDAO = null;

    public MaPtBuyReqHdrListDAO getMaPtBuyReqHdrListDAO() {
		return maPtBuyReqHdrListDAO;
	}

	public void setMaPtBuyReqHdrListDAO(MaPtBuyReqHdrListDAO maPtBuyReqHdrListDAO) {
		this.maPtBuyReqHdrListDAO = maPtBuyReqHdrListDAO;
	}

	public List findBuyList(MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, User user)
    {      
        return maPtBuyReqHdrListDAO.findBuyList(maPtBuyReqHdrCommonDTO,user);
    }
	
	public int deleteBuy(String[] deleteRows, User user) throws Exception{
	    MaPtBuyReqListService maPtBuyReqListService = (MaPtBuyReqListService) CommonUtil.getBean("maPtBuyReqListService", user);
	    MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO = new MaPtBuyReqHdrCommonDTO();
	    MaPtBuyReqListDTO maPtBuyReqListDTO = new MaPtBuyReqListDTO();
	    List<Map> list;
	    List<String> ptprlistIdList = new ArrayList<String>();
	    List<String> ptpritemIdList = new ArrayList<String>();
	    List<String> ptpnlistIdList = new ArrayList<String>();
        int result = 0;
        if(!deleteRows.equals(null)) {
            for(String id : deleteRows)
            {
                int cnt = maPtBuyReqHdrListDAO.deleteBuy(id,user);
                result = result + cnt;
                
                if(cnt>0){
                    maPtBuyReqHdrCommonDTO.setPtPrListId(id);
                    list = maPtBuyReqListService.findItemList(maPtBuyReqHdrCommonDTO, maPtBuyReqListDTO, user);
                    for(Map map:list){
                        ptprlistIdList.add(id);
                        ptpritemIdList.add(StringUtil.valueOf(map.get("PTPRITEMID")));
                        ptpnlistIdList.add(StringUtil.valueOf(map.get("PTPNLISTID")));
                    }
                }
            }
            maPtBuyReqListService.deleteItemList(ptprlistIdList.toArray(new String[0]), ptpritemIdList.toArray(new String[0]), ptpnlistIdList.toArray(new String[0]), user);
        }
        
        return result;
    }

	@Override
	public String findTotalCount(MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, User user)
	{
		return maPtBuyReqHdrListDAO.findTotalCount(maPtBuyReqHdrCommonDTO, user);
	}
}


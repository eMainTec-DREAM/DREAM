package dream.part.pur.buy.service.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import common.bean.MwareConfig;
import common.bean.User;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.StringUtil;
import dream.part.pur.buy.dao.MaPtBuyReqDetailDAO;
import dream.part.pur.buy.dto.MaPtBuyReqDetailDTO;
import dream.part.pur.buy.dto.MaPtBuyReqHdrCommonDTO;
import dream.part.pur.buy.dto.MaPtBuyReqListDTO;
import dream.part.pur.buy.service.MaPtBuyReqDetailService;
import dream.part.pur.buy.service.MaPtBuyReqListService;
import dream.part.pur.po.dto.PartPurPoItemDTO;
import dream.part.pur.po.service.PartPurPoItemService;
import dream.part.pur.req.dto.MaPtPurReqDetailDTO;
import dream.part.pur.req.dto.MaPtReqCommonDTO;
import dream.part.pur.req.service.MaPtPurReqDetailService;

/**
 * 구매신청item - 상세
 * @author kim2107
 * @version $Id: MaPtBuyReqDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maPtBuyReqDetailServiceTarget"
 * @spring.txbn id="maPtBuyReqDetailService"
 * @spring.property name="maPtBuyReqDetailDAO" ref="maPtBuyReqDetailDAO"
 */
public class MaPtBuyReqDetailServiceImpl implements MaPtBuyReqDetailService
{
    private MaPtBuyReqDetailDAO maPtBuyReqDetailDAO = null;
    
    public MaPtBuyReqDetailDAO getMaPtBuyReqDetailDAO() {
		return maPtBuyReqDetailDAO;
	}

	public void setMaPtBuyReqDetailDAO(MaPtBuyReqDetailDAO maPtBuyReqDetailDAO) {
		this.maPtBuyReqDetailDAO = maPtBuyReqDetailDAO;
	}
  
	public MaPtBuyReqDetailDTO findDetail(MaPtBuyReqListDTO maPtBuyReqListDTO, MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, User user)throws Exception
    {
	    MaPtBuyReqListService maPtBuyReqListService = (MaPtBuyReqListService) CommonUtil.getBean("maPtBuyReqListService");
        return (MaPtBuyReqDetailDTO) CommonUtil.makeDetailFromList(maPtBuyReqListService.findItemList(maPtBuyReqHdrCommonDTO, maPtBuyReqListDTO, user), new MaPtBuyReqDetailDTO());
    }
    
	public int updateDetail(MaPtBuyReqDetailDTO maPtBuyReqDetailDTO, MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, User user) throws Exception
    {        
		
		int result = maPtBuyReqDetailDAO.updateDetail(maPtBuyReqDetailDTO, maPtBuyReqHdrCommonDTO, user);
		
		// 현장구매청구 목록에서 가져온 데이터면 pr수량, 상태 업데이트
		if (!CommonUtil.isNullCheck(maPtBuyReqDetailDTO.getPtPnListId())) {
			MaPtPurReqDetailService maPtPurReqDetailService = (MaPtPurReqDetailService) CommonUtil.getBean("maPtPurReqDetailService", user);
            MaPtReqCommonDTO maPtReqCommonDTO = new MaPtReqCommonDTO();
            maPtReqCommonDTO.setReqId(maPtBuyReqDetailDTO.getPtPnListId());
            MaPtPurReqDetailDTO maPtPurReqDetailDTO = maPtPurReqDetailService.findDetail(maPtReqCommonDTO, user);
            maPtPurReqDetailDTO.setRecDate(DateUtil.getTimeStamp(user.getOffset()));
            maPtPurReqDetailDTO = maPtPurReqDetailService.getQty(maPtPurReqDetailDTO, user);
            maPtPurReqDetailDTO = maPtPurReqDetailService.getStatus(maPtPurReqDetailDTO, user);
            maPtPurReqDetailService.updateDetail(maPtPurReqDetailDTO, user);
		}
		
        return result;
    }
	public int insertDetail(MaPtBuyReqDetailDTO maPtBuyReqDetailDTO, MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO, User user) throws Exception
    {        
		if("".equals(maPtBuyReqDetailDTO.getPartGrade())){
			maPtBuyReqDetailDTO.setPartGrade(MwareConfig.getPartGrade());
		}
		
		int result = maPtBuyReqDetailDAO.insertDetail( maPtBuyReqDetailDTO, maPtBuyReqHdrCommonDTO, user);
		
		// 현장구매청구 목록에서 가져온 데이터면 접수일자, pr수량, 상태 업데이트
		if (!CommonUtil.isNullCheck(maPtBuyReqDetailDTO.getPtPnListId())) {
		    MaPtPurReqDetailService maPtPurReqDetailService = (MaPtPurReqDetailService) CommonUtil.getBean("maPtPurReqDetailService", user);
            MaPtReqCommonDTO maPtReqCommonDTO = new MaPtReqCommonDTO();
            maPtReqCommonDTO.setReqId(maPtBuyReqDetailDTO.getPtPnListId());
            MaPtPurReqDetailDTO maPtPurReqDetailDTO = maPtPurReqDetailService.findDetail(maPtReqCommonDTO, user);
            maPtPurReqDetailDTO.setRecDate(DateUtil.getTimeStamp(user.getOffset()));
            maPtPurReqDetailDTO = maPtPurReqDetailService.getQty(maPtPurReqDetailDTO, user);
            maPtPurReqDetailDTO = maPtPurReqDetailService.getStatus(maPtPurReqDetailDTO, user);
            maPtPurReqDetailService.updateDetail(maPtPurReqDetailDTO, user);
		}
		
		return result;
    }
	
	@Override
	public MaPtBuyReqDetailDTO getQty(MaPtBuyReqDetailDTO maPtBuyReqDetailDTO, User user) throws Exception
    {
        double poOnQty = 0;
        double poQty = 0;
        double grOnQty = 0;
        double grQty = 0;
        //PO 상태 : 작성중
        String[] uncompleteStatus = {"W"};
        List<String> uncompleteStatusList = new ArrayList<String>(Arrays.asList(uncompleteStatus));
        
        PartPurPoItemService partPurPoItemService = (PartPurPoItemService) CommonUtil.getBean("partPurPoItemService", user);
        PartPurPoItemDTO partPurPoItemDTO = new PartPurPoItemDTO();
        partPurPoItemDTO.setPtPrItemId(maPtBuyReqDetailDTO.getPtPrItemId());
        List<Map> list = partPurPoItemService.findList(partPurPoItemDTO, user);
        for(Map map:list)
        {
            poOnQty += toDouble(StringUtil.valueOf(map.get("POQTY")));
            if(!uncompleteStatusList.contains(StringUtil.valueOf(map.get("POLISTSTATUS")))){
                poQty += toDouble(StringUtil.valueOf(map.get("POQTY")));
            }
            grOnQty += toDouble(StringUtil.valueOf(map.get("GRONQTY")));
            grQty += toDouble(StringUtil.valueOf(map.get("GRQTY")));
        }
        
        maPtBuyReqDetailDTO.setPoOnQty(String.valueOf(poOnQty));
        maPtBuyReqDetailDTO.setPoQty(String.valueOf(poQty));
        maPtBuyReqDetailDTO.setGrOnQty(String.valueOf(grOnQty));
        maPtBuyReqDetailDTO.setGrQty(String.valueOf(grQty));
        
        return maPtBuyReqDetailDTO;
    }
	private double toDouble(String str)
    {
        try{
            return "".equals(str)?0:Double.parseDouble(str);
        }catch(NumberFormatException nfe){
            nfe.printStackTrace();
            return 0;
        }
    }
}

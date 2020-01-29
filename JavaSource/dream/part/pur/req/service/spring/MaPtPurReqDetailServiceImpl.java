package dream.part.pur.req.service.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import common.util.MailUtil;
import common.util.StringUtil;
import dream.mgr.message.service.MgrMessageTransDetailService;
import dream.part.pur.buy.dto.MaPtBuyReqHdrCommonDTO;
import dream.part.pur.buy.dto.MaPtBuyReqListDTO;
import dream.part.pur.buy.service.MaPtBuyReqListService;
import dream.part.pur.req.dao.MaPtPurReqDetailDAO;
import dream.part.pur.req.dto.MaPtPurReqDetailDTO;
import dream.part.pur.req.dto.MaPtReqCommonDTO;
import dream.part.pur.req.service.MaPtPurReqDetailService;

/**
 * 부품수리 - 상세 serviceimpl 
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtPurReqDetailServiceTarget"
 * @spring.txbn id="maPtPurReqDetailService"
 * @spring.property name="maPtPurReqDetailDAO" ref="maPtPurReqDetailDAO"
 * @spring.property name="mgrMessageTransDetailService" ref="mgrMessageTransDetailService"
 */
public class MaPtPurReqDetailServiceImpl implements MaPtPurReqDetailService
{
    private MaPtPurReqDetailDAO maPtPurReqDetailDAO = null;
    private MgrMessageTransDetailService mgrMessageTransDetailService = null;
    
    public MgrMessageTransDetailService getMgrMessageTransDetailService()
    {
        return mgrMessageTransDetailService;
    }

    public void setMgrMessageTransDetailService(
            MgrMessageTransDetailService mgrMessageTransDetailService)
    {
        this.mgrMessageTransDetailService = mgrMessageTransDetailService;
    }

    public MaPtPurReqDetailDAO getMaPtPurReqDetailDAO() 
    {
		return maPtPurReqDetailDAO;
	}

	public void setMaPtPurReqDetailDAO(MaPtPurReqDetailDAO maPtPurReqDetailDAO) 
	{
		this.maPtPurReqDetailDAO = maPtPurReqDetailDAO;
	}

	public MaPtPurReqDetailDTO findDetail(MaPtReqCommonDTO maPtReqCommonDTO, User loginUser)throws Exception
    {
        return maPtPurReqDetailDAO.findDetail(maPtReqCommonDTO, loginUser);
    }
	
	public int insertDetail(MaPtPurReqDetailDTO maPtPurReqDetailDTO, User loginUser) throws Exception
    {   
        return maPtPurReqDetailDAO.insertDetail(maPtPurReqDetailDTO, loginUser);
    }
	
	public int updateDetail(MaPtPurReqDetailDTO maPtPurReqDetailDTO, User loginUser) throws Exception
    {        
        return maPtPurReqDetailDAO.updateDetail(maPtPurReqDetailDTO, loginUser);
    }
	
	@Override
	public MaPtPurReqDetailDTO getQty(MaPtPurReqDetailDTO maPtPurReqDetailDTO, User user) throws Exception
	{
	    double prOnQty = 0;
	    double prQty = 0;
	    double poOnQty = 0;
	    double poQty = 0;
	    double grOnQty = 0;
	    double grQty = 0;
	    //PR 상태 : 작성중, 결재반려, 결재중, 결재요청
	    String[] uncompleteStatus = {"W", "WDA", "WOA", "WRA"};
	    List<String> uncompleteStatusList = new ArrayList<String>(Arrays.asList(uncompleteStatus));
	    
	    MaPtBuyReqListService maPtBuyReqListService = (MaPtBuyReqListService) CommonUtil.getBean("maPtBuyReqListService", user);
	    MaPtBuyReqListDTO maPtBuyReqListDTO = new MaPtBuyReqListDTO();
        maPtBuyReqListDTO.setPtPnListId(maPtPurReqDetailDTO.getReqId());
        List<Map> list = maPtBuyReqListService.findItemList(new MaPtBuyReqHdrCommonDTO(), maPtBuyReqListDTO, user);
        for(Map map:list)
        {
            prOnQty += toDouble(StringUtil.valueOf(map.get("RECQTY")));
            if(!uncompleteStatusList.contains(StringUtil.valueOf(map.get("PTPRLISTSTATUS")))){
                prQty += toDouble(StringUtil.valueOf(map.get("RECQTY")));
            }
            poOnQty += toDouble(StringUtil.valueOf(map.get("POONQTY")));
            poQty += toDouble(StringUtil.valueOf(map.get("POQTY")));
            grOnQty += toDouble(StringUtil.valueOf(map.get("GRONQTY")));
            grQty += toDouble(StringUtil.valueOf(map.get("GRQTY")));
        }
        
        maPtPurReqDetailDTO.setPrOnQty(String.valueOf(prOnQty));
        maPtPurReqDetailDTO.setPrQty(String.valueOf(prQty));
        maPtPurReqDetailDTO.setPoOnQty(String.valueOf(poOnQty));
        maPtPurReqDetailDTO.setPoQty(String.valueOf(poQty));
        maPtPurReqDetailDTO.setGrOnQty(String.valueOf(grOnQty));
        maPtPurReqDetailDTO.setGrQty(String.valueOf(grQty));
        
	    return maPtPurReqDetailDTO;
	}
	
	@Override
	public MaPtPurReqDetailDTO getStatus(MaPtPurReqDetailDTO maPtPurReqDetailDTO, User user) throws Exception
	{
	    double pnQty = toDouble(CommonUtil.getRowMoneyToNum(maPtPurReqDetailDTO.getQty()));
	    double prOnQty = toDouble(maPtPurReqDetailDTO.getPrOnQty());
	    double prQty = toDouble(maPtPurReqDetailDTO.getPrQty());
	    double poOnQty = toDouble(maPtPurReqDetailDTO.getPoOnQty());
	    double poQty = toDouble(maPtPurReqDetailDTO.getPoQty());
	    double grOnQty = toDouble(maPtPurReqDetailDTO.getGrOnQty());
	    double grQty = toDouble(maPtPurReqDetailDTO.getGrQty());
	    //C > PRW > PRC > POW > POC > GRW > GRC
	    if(prOnQty==0) maPtPurReqDetailDTO.setInputStatus("C");
	    if(prOnQty>0) maPtPurReqDetailDTO.setInputStatus("PRW");
	    if(pnQty<=prQty) maPtPurReqDetailDTO.setInputStatus("PRC");
	    if(poOnQty>0) maPtPurReqDetailDTO.setInputStatus("POW");
	    if(pnQty<=poQty) maPtPurReqDetailDTO.setInputStatus("POC");
	    if(grOnQty>0) maPtPurReqDetailDTO.setInputStatus("GRW");
	    if(pnQty<=grQty) maPtPurReqDetailDTO.setInputStatus("GRC");
	    
	    return maPtPurReqDetailDTO;
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
	
	public int updateStatus(MaPtPurReqDetailDTO maPtPurReqDetailDTO, final User loginUser) throws Exception
	{      
	    int result = 0;
        
        String chkPurStatus = maPtPurReqDetailDAO.chkPurStatus(maPtPurReqDetailDTO, loginUser);
        
        if(!"0".equals(chkPurStatus)) {
            
            result += maPtPurReqDetailDAO.updateStatus(maPtPurReqDetailDTO, loginUser);
            
            MailUtil.sendMail("PPR10", maPtPurReqDetailDTO.getReqId(), loginUser);
        }
        
        return result;
	}	
	public int recStatus(MaPtPurReqDetailDTO maPtPurReqDetailDTO, User loginUser) throws Exception
	{      
		return maPtPurReqDetailDAO.recStatus(maPtPurReqDetailDTO, loginUser);
	}

	@Override
	public int recCancel(MaPtPurReqDetailDTO maPtPurReqDetailDTO, User loginUser) {
		return maPtPurReqDetailDAO.recCancel(maPtPurReqDetailDTO, loginUser);
	}	
}
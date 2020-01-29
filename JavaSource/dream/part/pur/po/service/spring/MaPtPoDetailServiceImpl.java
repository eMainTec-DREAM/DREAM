package dream.part.pur.po.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import common.util.StringUtil;
import dream.part.pur.po.dao.MaPtPoDetailDAO;
import dream.part.pur.po.dto.MaPtPoDetailDTO;
import dream.part.pur.po.dto.PartPurPoItemDTO;
import dream.part.pur.po.service.MaPtPoDetailService;
import dream.part.pur.po.service.PartPurPoItemService;

/**
 * 발주이력 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtPoDetailServiceTarget"
 * @spring.txbn id="maPtPoDetailService"
 * @spring.property name="maPtPoDetailDAO" ref="maPtPoDetailDAO"
 */
public class MaPtPoDetailServiceImpl implements MaPtPoDetailService
{
    private MaPtPoDetailDAO maPtPoDetailDAO = null;
    
    public MaPtPoDetailDAO getMaPtPoDetailDAO() 
    {
		return maPtPoDetailDAO;
	}

	public void setMaPtPoDetailDAO(MaPtPoDetailDAO maPtPoDetailDAO) 
	{
		this.maPtPoDetailDAO = maPtPoDetailDAO;
	}

	public MaPtPoDetailDTO findDetail(String poListId, User user)throws Exception
    {
        return maPtPoDetailDAO.findDetail(poListId, user);
    }
	
	public int insertDetail(MaPtPoDetailDTO maPtPoDetailDTO, User user) throws Exception
    {   
        return maPtPoDetailDAO.insertDetail(maPtPoDetailDTO, user);
    }
	
	public int updateDetail(MaPtPoDetailDTO maPtPoDetailDTO, User user) throws Exception
    {   
        return maPtPoDetailDAO.updateDetail(maPtPoDetailDTO, user);
    }
	
	public int updatePtPoListStatus(MaPtPoDetailDTO maPtPoDetailDTO, User user) throws Exception
	{     
	    return maPtPoDetailDAO.updatePtPoListStatus(maPtPoDetailDTO, user);
	}
	
	@Override
	public MaPtPoDetailDTO getStatus(MaPtPoDetailDTO maPtPoDetailDTO, User user) throws Exception
    {
        double poQty = 0;
        double grOnQty = 0;
        double grQty = 0;
        PartPurPoItemService partPurPoItemService = (PartPurPoItemService) CommonUtil.getBean("partPurPoItemService", user);
        PartPurPoItemDTO partPurPoItemDTO = new PartPurPoItemDTO();
        partPurPoItemDTO.setPoListId(maPtPoDetailDTO.getPoListId());
        List<Map> list = partPurPoItemService.findList(partPurPoItemDTO, user);
        for(Map map:list){
            poQty += toDouble(StringUtil.valueOf(map.get("POQTY")));
            grOnQty += toDouble(StringUtil.valueOf(map.get("GRONQTY")));
            grQty += toDouble(StringUtil.valueOf(map.get("GRQTY")));
        }
        
        //C > GRW > GRC
        if(grOnQty==0) maPtPoDetailDTO.setPoStatusId("C");
        if(grOnQty>0) maPtPoDetailDTO.setPoStatusId("GRW");
        if(poQty<=grQty) maPtPoDetailDTO.setPoStatusId("GRC");
        
        return maPtPoDetailDTO;
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

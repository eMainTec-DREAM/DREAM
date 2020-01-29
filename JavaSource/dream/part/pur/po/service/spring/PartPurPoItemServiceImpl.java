package dream.part.pur.po.service.spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import common.util.StringUtil;
import dream.part.pur.buy.dto.MaPtBuyReqDetailDTO;
import dream.part.pur.buy.dto.MaPtBuyReqHdrCommonDTO;
import dream.part.pur.buy.dto.MaPtBuyReqHdrDetailDTO;
import dream.part.pur.buy.dto.MaPtBuyReqListDTO;
import dream.part.pur.buy.service.MaPtBuyReqDetailService;
import dream.part.pur.buy.service.MaPtBuyReqHdrDetailService;
import dream.part.pur.po.dao.PartPurPoItemDAO;
import dream.part.pur.po.dto.PartPurPoItemDTO;
import dream.part.pur.po.service.PartPurPoItemService;
import dream.part.rec.dto.MaPtRecCommonDTO;
import dream.part.rec.service.MaPtRecListService;

/**
 * 발주품목 - serviceimpl
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="partPurPoItemServiceTarget"
 * @spring.txbn id="partPurPoItemService"
 * @spring.property name="partPurPoItemDAO" ref="partPurPoItemDAO"
 */
public class PartPurPoItemServiceImpl implements PartPurPoItemService
{
    private PartPurPoItemDAO partPurPoItemDAO = null;

    public PartPurPoItemDAO getPartPurPoItemDAO()
    {
        return partPurPoItemDAO;
    }

    public void setPartPurPoItemDAO(PartPurPoItemDAO partPurPoItemDAO)
    {
        this.partPurPoItemDAO = partPurPoItemDAO;
    }

    @Override
    public List findList(PartPurPoItemDTO partPurPoItemDTO, User user) throws Exception
    {
        return partPurPoItemDAO.find(partPurPoItemDTO, user);
    }
    
    @Override
    public PartPurPoItemDTO findDetail(PartPurPoItemDTO partPurPoItemDTO, User user) throws Exception
    {
        return (PartPurPoItemDTO) CommonUtil.makeDetailFromList(this.findList(partPurPoItemDTO, user), partPurPoItemDTO);
    }

    @Override
    public int insertDetail(PartPurPoItemDTO partPurPoItemDTO, User user) throws Exception
    {
        int result = partPurPoItemDAO.insert(partPurPoItemDTO, user);
        
        //구매요청품목과 연결되어 있다면 구매요청의 po수량과 상태 업데이트
        if(!CommonUtil.isNullCheck(partPurPoItemDTO.getPtPrItemId())){
            //po수량
            MaPtBuyReqDetailService maPtBuyReqDetailService = (MaPtBuyReqDetailService) CommonUtil.getBean("maPtBuyReqDetailService", user);
            MaPtBuyReqListDTO maPtBuyReqListDTO = new MaPtBuyReqListDTO();
            MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO = new MaPtBuyReqHdrCommonDTO();
            maPtBuyReqListDTO.setPtPrItemId(partPurPoItemDTO.getPtPrItemId());
            MaPtBuyReqDetailDTO maPtBuyReqDetailDTO = maPtBuyReqDetailService.findDetail(maPtBuyReqListDTO, maPtBuyReqHdrCommonDTO, user);
            maPtBuyReqDetailDTO = maPtBuyReqDetailService.getQty(maPtBuyReqDetailDTO, user);
            maPtBuyReqHdrCommonDTO.setPtPrListId(maPtBuyReqDetailDTO.getPtPrListId());
            maPtBuyReqDetailService.updateDetail(maPtBuyReqDetailDTO, maPtBuyReqHdrCommonDTO, user);
            
            //상태
            MaPtBuyReqHdrDetailService maPtBuyReqHdrDetailService = (MaPtBuyReqHdrDetailService) CommonUtil.getBean("maPtBuyReqHdrDetailService", user);
            MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO = maPtBuyReqHdrDetailService.findDetail(maPtBuyReqHdrCommonDTO, user);
            maPtBuyReqHdrDetailDTO = maPtBuyReqHdrDetailService.getStatus(maPtBuyReqHdrDetailDTO, user);
            maPtBuyReqHdrDetailService.updateDetail(maPtBuyReqHdrDetailDTO, user);
        }
        
        return result;
    }
    
    @Override
    public int updateDetail(PartPurPoItemDTO partPurPoItemDTO, User user) throws Exception
    {
        int result = partPurPoItemDAO.update(partPurPoItemDTO, user);
        
        //구매요청품목과 연결되어 있다면 구매요청의 po수량과 상태 업데이트
        if(!CommonUtil.isNullCheck(partPurPoItemDTO.getPtPrItemId())){
            //po수량
            MaPtBuyReqDetailService maPtBuyReqDetailService = (MaPtBuyReqDetailService) CommonUtil.getBean("maPtBuyReqDetailService", user);
            MaPtBuyReqListDTO maPtBuyReqListDTO = new MaPtBuyReqListDTO();
            MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO = new MaPtBuyReqHdrCommonDTO();
            maPtBuyReqListDTO.setPtPrItemId(partPurPoItemDTO.getPtPrItemId());
            MaPtBuyReqDetailDTO maPtBuyReqDetailDTO = maPtBuyReqDetailService.findDetail(maPtBuyReqListDTO, maPtBuyReqHdrCommonDTO, user);
            maPtBuyReqDetailDTO = maPtBuyReqDetailService.getQty(maPtBuyReqDetailDTO, user);
            maPtBuyReqHdrCommonDTO.setPtPrListId(maPtBuyReqDetailDTO.getPtPrListId());
            maPtBuyReqDetailService.updateDetail(maPtBuyReqDetailDTO, maPtBuyReqHdrCommonDTO, user);
            
            //상태
            MaPtBuyReqHdrDetailService maPtBuyReqHdrDetailService = (MaPtBuyReqHdrDetailService) CommonUtil.getBean("maPtBuyReqHdrDetailService", user);
            MaPtBuyReqHdrDetailDTO maPtBuyReqHdrDetailDTO = maPtBuyReqHdrDetailService.findDetail(maPtBuyReqHdrCommonDTO, user);
            maPtBuyReqHdrDetailDTO = maPtBuyReqHdrDetailService.getStatus(maPtBuyReqHdrDetailDTO, user);
            maPtBuyReqHdrDetailService.updateDetail(maPtBuyReqHdrDetailDTO, user);
        }
        
        return result;
    }
    
    @Override
    public PartPurPoItemDTO getQty(PartPurPoItemDTO partPurPoItemDTO, User user) throws Exception
    {
        double grOnQty = 0;
        double grQty = 0;
        //GR 상태 : 작성중
        String[] uncompleteStatus = {"W"};
        List<String> uncompleteStatusList = new ArrayList<String>(Arrays.asList(uncompleteStatus));
        
        MaPtRecListService maPtRecListService = (MaPtRecListService) CommonUtil.getBean("maPtRecListService", user);
        MaPtRecCommonDTO maPtRecCommonDTO = new MaPtRecCommonDTO();
        maPtRecCommonDTO.setPtPoItemId(partPurPoItemDTO.getPtPoItemId());
        List<Map> list = maPtRecListService.findList(maPtRecCommonDTO, user);
        for(Map map:list)
        {
            grOnQty += toDouble(StringUtil.valueOf(map.get("RECQTY")));
            if(!uncompleteStatusList.contains(StringUtil.valueOf(map.get("PRRECLISTSTATUS")))){
                grQty += toDouble(StringUtil.valueOf(map.get("RECQTY")));
            }
        }
        
        partPurPoItemDTO.setGrOnQty(String.valueOf(grOnQty));
        partPurPoItemDTO.setGrQty(String.valueOf(grQty));
        
        return partPurPoItemDTO;
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
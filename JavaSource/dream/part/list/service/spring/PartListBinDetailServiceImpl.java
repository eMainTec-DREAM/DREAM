package dream.part.list.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.part.list.dao.PartListBinDetailDAO;
import dream.part.list.dto.PartListBinDetailDTO;
import dream.part.list.dto.PartListBinListDTO;
import dream.part.list.service.PartListBinDetailService;
import dream.part.list.service.PartListBinListService;
import dream.part.stk.dto.MaPtStckCommonDTO;
import dream.part.stk.dto.MaPtStckDetailDTO;
import dream.part.stk.service.MaPtStckDetailService;

/**
 * 부품창고 보관위치 - Detail Service implements
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="partListBinDetailServiceTarget"
 * @spring.txbn id="partListBinDetailService"
 * @spring.property name="partListBinDetailDAO" ref="partListBinDetailDAO"
 */
public class PartListBinDetailServiceImpl implements PartListBinDetailService
{
    private PartListBinDetailDAO partListBinDetailDAO = null;
    
    public PartListBinDetailDAO getPartListBinDetailDAO() {
    	return partListBinDetailDAO;
    }
    
    public void setPartListBinDetailDAO(PartListBinDetailDAO partListBinDetailDAO) {
    	this.partListBinDetailDAO = partListBinDetailDAO;
    }
    
    
    public PartListBinDetailDTO findPtWhBinDetail(PartListBinListDTO partListBinListDTO, User user) throws Exception
    {
    	return partListBinDetailDAO.findPtWhBinDetail(partListBinListDTO, user);
    }
    
    public int insertPtWhBinDetail(PartListBinDetailDTO partListBinDetailDTO, User user) throws Exception
    {
    	int result = 0;
    	
    	result = partListBinDetailDAO.insertPtWhBinDetail(partListBinDetailDTO, user);
    	
    	// 재고 저장위치 갱신처리
    	this.updateBinNo(partListBinDetailDTO, user);
    	
    	return result;
    }
    
    public int updatePtWhBinDetail(PartListBinDetailDTO partListBinDetailDTO, User user) throws Exception
    {
    	int result = 0;
    	
    	// 수정되기 전 보관위치 값 조회
    	PartListBinListDTO partListBinListDTO = new PartListBinListDTO();
    	partListBinListDTO.setPtwhBinNoId(partListBinDetailDTO.getPtwhBinNoId());
    	partListBinListDTO.setPartId(partListBinDetailDTO.getPartId());
    	PartListBinDetailDTO prePartListBinDetailDTO = partListBinDetailDAO.findPtWhBinDetail(partListBinListDTO, user);
    	
    	// 보관위치 수정    	
    	result = partListBinDetailDAO.updatePtWhBinDetail(partListBinDetailDTO, user);

    	// 창고명이 변경될 경우 이전창고 보관위치 갱신
    	if(!(prePartListBinDetailDTO.getWcodeId()).equals(partListBinDetailDTO.getWcodeId()))
    		this.updateBinNo(prePartListBinDetailDTO, user);
    	
    	// 재고 저장위치 갱신처리
    	this.updateBinNo(partListBinDetailDTO, user);
    	
    	return result;
    }

	public String validPtBin(PartListBinListDTO partListBinListDTO, PartListBinDetailDTO partListBinDetailDTO, User user) throws Exception
	{
        return partListBinDetailDAO.validPtBin(partListBinListDTO, partListBinDetailDTO, user);
	}
	
	public int updateBinNo(PartListBinDetailDTO partListBinDetailDTO, User user) throws Exception
	{
		int result = 0;

        // 현재고 조회를 위한 조건 셋팅
		MaPtStckDetailService maPtStckDetailService = (MaPtStckDetailService)CommonUtil.getBean("maPtStckDetailService", user.getCompNo());
		MaPtStckCommonDTO maPtStckCommonDTO = new MaPtStckCommonDTO();
		maPtStckCommonDTO.setCompNo(user.getCompNo());
		maPtStckCommonDTO.setPartId(partListBinDetailDTO.getPartId());
		maPtStckCommonDTO.setWcodeId(partListBinDetailDTO.getWcodeId());
		
		// 현재고 조회
		MaPtStckDetailDTO maPtStckDetailDTO = maPtStckDetailService.findDetail(maPtStckCommonDTO);

        // 해당 부품,창고 현재고가 존재하면 taptstock.bin_no를 갱신
		if(null!=maPtStckDetailDTO && !"".equals(maPtStckDetailDTO.getPartId()))
		{
			// 창고별 부품별 저장위치 조회(최대 6개까지)
			String stockABinNo = "";
			String stockBBinNo = "";
			
			PartListBinListDTO partListBinListDTO = new PartListBinListDTO();
			partListBinListDTO.setWcodeId(partListBinDetailDTO.getWcodeId());
			partListBinListDTO.setPartId(partListBinDetailDTO.getPartId());;
			
			// 해당 부품,창고명 보관위치 조회
			PartListBinListService partListBinListService = (PartListBinListService)CommonUtil.getBean("partListBinListService", user.getCompNo());
			List ptWhBinList = partListBinListService.findPtWhBinList(partListBinListDTO, user);
			
            if(ptWhBinList.size() != 0)
            {
            	for (int i = 0; i < ptWhBinList.size(); i++)
            	{
            		Map map = (Map)ptWhBinList.get(i);
            		PartListBinDetailDTO ptListBinDetailDTO = (PartListBinDetailDTO) CommonUtil.makeDTO(map, PartListBinDetailDTO.class);
            		
            		// A보관위치 
            		if("A".equals(ptListBinDetailDTO.getPartGrade()))
    				{
            			if("".equals(stockABinNo))
            				stockABinNo += ptListBinDetailDTO.getBinNo();
            			else
            				stockABinNo += ", " + ptListBinDetailDTO.getBinNo();
            			
            		}
            		// B보관위치 
            		else if("B".equals(ptListBinDetailDTO.getPartGrade()))
            		{
            			if("".equals(stockBBinNo))
            				stockBBinNo += ptListBinDetailDTO.getBinNo();
            			else
            				stockBBinNo += ", " + ptListBinDetailDTO.getBinNo();
            			
            		}
				}
            	
            }
			// A 재고등급 보관위치 셋팅
			maPtStckDetailDTO.setAbinNo(stockABinNo);
			// B 재고등급 보관위치 셋팅
			maPtStckDetailDTO.setBbinNo(stockBBinNo);

			//보관위치 업데이트
			maPtStckDetailDTO.setCompNo(user.getCompNo());				
			result = maPtStckDetailService.updateDetail(maPtStckDetailDTO, user);
		}
		
		return result;
	}
	
}

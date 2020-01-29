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
 * ��ǰâ�� ������ġ - Detail Service implements
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
    	
    	// ��� ������ġ ����ó��
    	this.updateBinNo(partListBinDetailDTO, user);
    	
    	return result;
    }
    
    public int updatePtWhBinDetail(PartListBinDetailDTO partListBinDetailDTO, User user) throws Exception
    {
    	int result = 0;
    	
    	// �����Ǳ� �� ������ġ �� ��ȸ
    	PartListBinListDTO partListBinListDTO = new PartListBinListDTO();
    	partListBinListDTO.setPtwhBinNoId(partListBinDetailDTO.getPtwhBinNoId());
    	partListBinListDTO.setPartId(partListBinDetailDTO.getPartId());
    	PartListBinDetailDTO prePartListBinDetailDTO = partListBinDetailDAO.findPtWhBinDetail(partListBinListDTO, user);
    	
    	// ������ġ ����    	
    	result = partListBinDetailDAO.updatePtWhBinDetail(partListBinDetailDTO, user);

    	// â����� ����� ��� ����â�� ������ġ ����
    	if(!(prePartListBinDetailDTO.getWcodeId()).equals(partListBinDetailDTO.getWcodeId()))
    		this.updateBinNo(prePartListBinDetailDTO, user);
    	
    	// ��� ������ġ ����ó��
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

        // ����� ��ȸ�� ���� ���� ����
		MaPtStckDetailService maPtStckDetailService = (MaPtStckDetailService)CommonUtil.getBean("maPtStckDetailService", user.getCompNo());
		MaPtStckCommonDTO maPtStckCommonDTO = new MaPtStckCommonDTO();
		maPtStckCommonDTO.setCompNo(user.getCompNo());
		maPtStckCommonDTO.setPartId(partListBinDetailDTO.getPartId());
		maPtStckCommonDTO.setWcodeId(partListBinDetailDTO.getWcodeId());
		
		// ����� ��ȸ
		MaPtStckDetailDTO maPtStckDetailDTO = maPtStckDetailService.findDetail(maPtStckCommonDTO);

        // �ش� ��ǰ,â�� ����� �����ϸ� taptstock.bin_no�� ����
		if(null!=maPtStckDetailDTO && !"".equals(maPtStckDetailDTO.getPartId()))
		{
			// â�� ��ǰ�� ������ġ ��ȸ(�ִ� 6������)
			String stockABinNo = "";
			String stockBBinNo = "";
			
			PartListBinListDTO partListBinListDTO = new PartListBinListDTO();
			partListBinListDTO.setWcodeId(partListBinDetailDTO.getWcodeId());
			partListBinListDTO.setPartId(partListBinDetailDTO.getPartId());;
			
			// �ش� ��ǰ,â��� ������ġ ��ȸ
			PartListBinListService partListBinListService = (PartListBinListService)CommonUtil.getBean("partListBinListService", user.getCompNo());
			List ptWhBinList = partListBinListService.findPtWhBinList(partListBinListDTO, user);
			
            if(ptWhBinList.size() != 0)
            {
            	for (int i = 0; i < ptWhBinList.size(); i++)
            	{
            		Map map = (Map)ptWhBinList.get(i);
            		PartListBinDetailDTO ptListBinDetailDTO = (PartListBinDetailDTO) CommonUtil.makeDTO(map, PartListBinDetailDTO.class);
            		
            		// A������ġ 
            		if("A".equals(ptListBinDetailDTO.getPartGrade()))
    				{
            			if("".equals(stockABinNo))
            				stockABinNo += ptListBinDetailDTO.getBinNo();
            			else
            				stockABinNo += ", " + ptListBinDetailDTO.getBinNo();
            			
            		}
            		// B������ġ 
            		else if("B".equals(ptListBinDetailDTO.getPartGrade()))
            		{
            			if("".equals(stockBBinNo))
            				stockBBinNo += ptListBinDetailDTO.getBinNo();
            			else
            				stockBBinNo += ", " + ptListBinDetailDTO.getBinNo();
            			
            		}
				}
            	
            }
			// A ����� ������ġ ����
			maPtStckDetailDTO.setAbinNo(stockABinNo);
			// B ����� ������ġ ����
			maPtStckDetailDTO.setBbinNo(stockBBinNo);

			//������ġ ������Ʈ
			maPtStckDetailDTO.setCompNo(user.getCompNo());				
			result = maPtStckDetailService.updateDetail(maPtStckDetailDTO, user);
		}
		
		return result;
	}
	
}

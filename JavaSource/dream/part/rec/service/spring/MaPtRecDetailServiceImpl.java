package dream.part.rec.service.spring;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrDetailDTO;
import dream.part.list.dto.PartListBinDetailDTO;
import dream.part.list.dto.PartListBinListDTO;
import dream.part.list.service.MaPtMstrDetailService;
import dream.part.list.service.PartListBinDetailService;
import dream.part.list.service.PartListBinListService;
import dream.part.pur.po.dto.MaPtPoDetailDTO;
import dream.part.pur.po.dto.PartPurPoItemDTO;
import dream.part.pur.po.service.MaPtPoDetailService;
import dream.part.pur.po.service.PartPurPoItemService;
import dream.part.rec.dao.MaPtRecDetailDAO;
import dream.part.rec.dto.MaPtRecDetailDTO;
import dream.part.rec.service.MaPtRecDetailService;

/**
 * �����԰� - �� serviceimpl 
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtRecDetailServiceTarget"
 * @spring.txbn id="maPtRecDetailService"
 * @spring.property name="maPtRecDetailDAO" ref="maPtRecDetailDAO"
 */
public class MaPtRecDetailServiceImpl implements MaPtRecDetailService
{
    private MaPtRecDetailDAO maPtRecDetailDAO = null;
    
    public MaPtRecDetailDAO getMaPtRecDetailDAO() 
    {
        return maPtRecDetailDAO;
    }

    public void setMaPtRecDetailDAO(MaPtRecDetailDAO maPtRecDetailDAO) 
    {
        this.maPtRecDetailDAO = maPtRecDetailDAO;
    }

    public MaPtRecDetailDTO findDetail(User user, String ptRecListId)throws Exception
    {
        MaPtRecDetailDTO maPtRecDetailDTO = maPtRecDetailDAO.findDetail(user, ptRecListId);
        
        return maPtRecDetailDTO;
    }
    
    public String checkExist(String ptRecListId)
    {
        return maPtRecDetailDAO.checkExistValue(ptRecListId);
    }
    
    public int insertDetail(MaPtRecDetailDTO maPtRecDetailDTO, User user) throws Exception
    {   
        int result = maPtRecDetailDAO.insertDetail(maPtRecDetailDTO, user);
        
        //����ǰ�񿡼� ������ �����͸� gr����, ���� ������Ʈ
        if(!CommonUtil.isNullCheck(maPtRecDetailDTO.getPoitemId())){
            //gr����
            PartPurPoItemService partPurPoItemService = (PartPurPoItemService) CommonUtil.getBean("partPurPoItemService", user);
            PartPurPoItemDTO partPurPoItemDTO = new PartPurPoItemDTO();
            partPurPoItemDTO.setPtPoItemId(maPtRecDetailDTO.getPoitemId());
            partPurPoItemDTO = partPurPoItemService.findDetail(partPurPoItemDTO, user);
            partPurPoItemDTO = partPurPoItemService.getQty(partPurPoItemDTO, user);
            partPurPoItemService.updateDetail(partPurPoItemDTO, user);
            
            //����
            MaPtPoDetailService maPtPoDetailService = (MaPtPoDetailService) CommonUtil.getBean("maPtPoDetailService", user);
            MaPtPoDetailDTO maPtPoDetailDTO = maPtPoDetailService.findDetail(partPurPoItemDTO.getPoListId(), user);
            maPtPoDetailDTO = maPtPoDetailService.getStatus(maPtPoDetailDTO, user);
            maPtPoDetailService.updateDetail(maPtPoDetailDTO, user);
        }
        
        return result;
    }
    
    public int updateDetail(MaPtRecDetailDTO maPtRecDetailDTO, User user) throws Exception
    {   
        int resultCnt = 0;
        String compNo = maPtRecDetailDTO.getCompNo();
        String prRecListId = maPtRecDetailDTO.getPrRecListId();
        
        // ���°� �԰�Ϸ�[C]�� �ƴ� ��츸 Update �Ѵ�. 
        String preRecListStatus = maPtRecDetailDAO.findPrRecListStatus(compNo, prRecListId);
        if(!"C".equals(preRecListStatus))
        {
            resultCnt = maPtRecDetailDAO.updateDetail(maPtRecDetailDTO,user);
            
            //����ǰ�񿡼� ������ �����͸� gr����, ���� ������Ʈ
            if(!CommonUtil.isNullCheck(maPtRecDetailDTO.getPoitemId())){
                //gr����
                PartPurPoItemService partPurPoItemService = (PartPurPoItemService) CommonUtil.getBean("partPurPoItemService", user);
                PartPurPoItemDTO partPurPoItemDTO = new PartPurPoItemDTO();
                partPurPoItemDTO.setPtPoItemId(maPtRecDetailDTO.getPoitemId());
                partPurPoItemDTO = partPurPoItemService.findDetail(partPurPoItemDTO, user);
                partPurPoItemDTO = partPurPoItemService.getQty(partPurPoItemDTO, user);
                partPurPoItemService.updateDetail(partPurPoItemDTO, user);
                
                //����
                MaPtPoDetailService maPtPoDetailService = (MaPtPoDetailService) CommonUtil.getBean("maPtPoDetailService", user);
                MaPtPoDetailDTO maPtPoDetailDTO = maPtPoDetailService.findDetail(partPurPoItemDTO.getPoListId(), user);
                maPtPoDetailDTO = maPtPoDetailService.getStatus(maPtPoDetailDTO, user);
                maPtPoDetailService.updateDetail(maPtPoDetailDTO, user);
            }
        }
        
//        String isSerial = maPtRecDetailDTO.getIsSerial();
        String isSerial = "N";
        //�ø����� �ƴѰ�� �ø��� ���̺� ����
        if(!"Y".equals(isSerial))
        {
            maPtRecDetailDAO.deleteSerial(maPtRecDetailDTO);
        }
        //�ø����ΰ�� �����ȣ�� serial ���̺�� �ٽ� ����
        else
        {
            maPtRecDetailDAO.updateSerialPart(maPtRecDetailDTO);
        }
        
        return resultCnt;
    }
    
    public int updatePtRecListStatus(MaPtRecDetailDTO maPtRecDetailDTO, User user) throws Exception
    {   
        int result = 0;
        //ǰ������
        result += makePtMstr(maPtRecDetailDTO,user);
        //�԰�Ȯ��
        result +=confirmPtRec(maPtRecDetailDTO,user);
        
        return result;
    }
    
    public String validPrRecListNo(MaPtRecDetailDTO maPtRecDetailDTO) throws Exception
    {
        return maPtRecDetailDAO.validPrRecListNo(maPtRecDetailDTO);
    }
    
    public String validSerialCount(MaPtRecDetailDTO maPtRecDetailDTO) throws Exception
    {
        return maPtRecDetailDAO.validSerialCount(maPtRecDetailDTO);
    }

    @Override
    public int confirmPtRec(MaPtRecDetailDTO maPtRecDetailDTO, User user) throws Exception {
        // �԰�Ϸ����� ���¸� ������ ��� �Ʒ� ���ν����� ȣ���Ѵ�. 
        int result = 0;
        if(!"".equals(maPtRecDetailDTO.getPartId()))
        {
        	// �̷� ���̺� id�� ������, �̷µ����� ���� �� ���ν��� ȣ��. 
        	String ptRecHistId = maPtRecDetailDAO.getNextSequence("SQAPTRECHIST_ID");
        	maPtRecDetailDAO.insertRecHistory(ptRecHistId, maPtRecDetailDTO, user);
        	maPtRecDetailDAO.executeSpPtInstock(user.getCompNo(), ptRecHistId);
        }

        result+=maPtRecDetailDAO.updatePtRecListStatus(maPtRecDetailDTO, user);            
        
        //����ǰ�񿡼� ������ �����͸� gr����, ���� ������Ʈ
        if(!CommonUtil.isNullCheck(maPtRecDetailDTO.getPoitemId())){
            //gr����
            PartPurPoItemService partPurPoItemService = (PartPurPoItemService) CommonUtil.getBean("partPurPoItemService", user);
            PartPurPoItemDTO partPurPoItemDTO = new PartPurPoItemDTO();
            partPurPoItemDTO.setPtPoItemId(maPtRecDetailDTO.getPoitemId());
            partPurPoItemDTO = partPurPoItemService.findDetail(partPurPoItemDTO, user);
            partPurPoItemDTO = partPurPoItemService.getQty(partPurPoItemDTO, user);
            partPurPoItemService.updateDetail(partPurPoItemDTO, user);
            
            //����
            MaPtPoDetailService maPtPoDetailService = (MaPtPoDetailService) CommonUtil.getBean("maPtPoDetailService", user);
            MaPtPoDetailDTO maPtPoDetailDTO = maPtPoDetailService.findDetail(partPurPoItemDTO.getPoListId(), user);
            maPtPoDetailDTO = maPtPoDetailService.getStatus(maPtPoDetailDTO, user);
            maPtPoDetailService.updateDetail(maPtPoDetailDTO, user);
        }
        
        // ����ó(��ǰ������) ������Ʈ
        updatePartsVendor(maPtRecDetailDTO, user);
        
        // ����� ������ġ ���� 
        updateBinNo(maPtRecDetailDTO, user);
        
        return result;
    }

    @Override
    public int confirmIsSerialPt(MaPtRecDetailDTO maPtRecDetailDTO, User user) throws Exception {
        
        if("C".equals(maPtRecDetailDTO.getPtRecMode())&&"Y".equals(maPtRecDetailDTO.getIsSerial()))
        {
            List serialList = maPtRecDetailDAO.findSerialList(maPtRecDetailDTO);
            
            Hashtable menuPathTable = new Hashtable();
            
            int pageMenuSize = serialList.size();

            Map tempMap = null;
            
            // List ����ŭ �ݺ��Ѵ�.
            for (int i=0; i<pageMenuSize; i++)
            {
                tempMap = (Map)serialList.get(i);
                
                // �ش� row map �� user_group �� object_type �� �����Ѵ�.
                String prreclistSerialId = String.valueOf(tempMap.get("PRRECLISTSERIALID"));
                String partId = String.valueOf(tempMap.get("PARTID"));
                String serialNo     = (String)tempMap.get("SERIALNO");   
                String isEquipId     = String.valueOf(tempMap.get("EQUIPID"));  
                
                //EqupId �� ������� �ʴٸ� ������ �̹� Ȯ���� �ؼ� ���� ������ ������.
                if("".equals(isEquipId)||isEquipId==null||"null".equals(isEquipId))
                {   

                    String wcodeId = maPtRecDetailDTO.getWcodeId();
                    String deptId = maPtRecDetailDTO.getDeptId();
                    String equipId = maPtRecDetailDAO.getNextSequence("SQAEQUIP_ID");
                    
                    maPtRecDetailDAO.insertEquipment(serialNo, user.getCompNo(),equipId,partId,deptId);
                    maPtRecDetailDAO.insertEqDevice(user.getCompNo(), equipId, partId,wcodeId );
                    maPtRecDetailDAO.updateSerial(user.getCompNo(), equipId, prreclistSerialId );
                }
                else
                {
                    maPtRecDetailDAO.updateEquipment(user.getCompNo(),isEquipId,"S");
                }
            }
    
        }
        //�ø��� ���縦 �԰�Ȯ�� ����Ұ�� ���� 
        else if("R".equals(maPtRecDetailDTO.getPtRecMode())&&"Y".equals(maPtRecDetailDTO.getIsSerial()))
        {
            List serialList = maPtRecDetailDAO.findSerialList(maPtRecDetailDTO);
            
            Hashtable menuPathTable = new Hashtable();
            
            int pageMenuSize = serialList.size();

            Map tempMap = null;
            
            // List ����ŭ �ݺ��Ѵ�.
            for (int i=0; i<pageMenuSize; i++)
            {
                tempMap = (Map)serialList.get(i);
                
                // �ش� row map �� user_group �� object_type �� �����Ѵ�.
                String prreclistSerialId = String.valueOf(tempMap.get("PRRECLISTSERIALID"));
                String partId = String.valueOf(tempMap.get("PARTID"));
                String serialNo     = (String)tempMap.get("SERIALNO");   
                String isEquipId     = String.valueOf(tempMap.get("EQUIPID")); 
                
                    maPtRecDetailDAO.updateEquipment(user.getCompNo(),isEquipId,"X");

            }
        }
        return 0;
    }

    @Override
    public int makePtMstr(MaPtRecDetailDTO maPtRecDetailDTO, User user) throws Exception {
        // ǰ���������ΰ� Y�� ��� ǰ��� �԰��� TAPARTS�� �����ϴ��� Ȯ���Ѵ�.
        int result = 0 ;
        if("Y".equals(maPtRecDetailDTO.getIsMakePartNoId()))
        {
            String existPartId ="";
            // �������� ���� ��� 0 return
            existPartId = maPtRecDetailDAO.checkExistPartNo(maPtRecDetailDTO,user);

            if("".equals(existPartId))
            {
                maPtRecDetailDTO.setPartId(maPtRecDetailDAO.getNextSequence("SQAPART_ID"));
                maPtRecDetailDTO.setPartNo(maPtRecDetailDTO.getPartId());
                
                maPtRecDetailDAO.insertPart(maPtRecDetailDTO,user);
            }
            else
            {
                maPtRecDetailDTO.setPartId(existPartId);
            }
            // ������Ʈ�Ͽ� part_id�� �־��ش�.
            result+=maPtRecDetailDAO.updatePartNo(maPtRecDetailDTO, user);
        }
        return result;
    }

    // ���޾�ü(����ó) ������Ʈ
    public void updatePartsVendor(MaPtRecDetailDTO maPtRecDetailDTO, User user) throws Exception
    {
    	MaPtMstrDetailService maPtMstrDetailService = (MaPtMstrDetailService) CommonUtil.getBean("maPtMstrDetailService");
    	
    	//update �� ���� ��ǰ ������ find
    	MaPtMstrCommonDTO maPtMstrCommonDTO = new MaPtMstrCommonDTO();
    	maPtMstrCommonDTO.setPartId(maPtRecDetailDTO.getPartId());
    	
    	MaPtMstrDetailDTO maPtMstrDetailDTO = maPtMstrDetailService.findDetail(maPtMstrCommonDTO, user);
    	
    	// ��ǰ �� ���� ����(����ó ����)
    	// �԰�ŷ�ó�� ������� ������ ����ó ����
    	if(!"".equals(maPtRecDetailDTO.getVendorId()) && null != maPtRecDetailDTO.getVendorId())
    	{
    		maPtMstrDetailDTO.setSeller(maPtRecDetailDTO.getVendorDesc());
    	}

    	maPtMstrDetailDTO.setCompNo(user.getCompNo());
    	
    	maPtMstrDetailService.updateDetail(maPtMstrDetailDTO, user);
    }
    
    // ������ġ(bin_no) ������Ʈ
    public void updateBinNo(MaPtRecDetailDTO maPtRecDetailDTO, User user) throws Exception
    {
    	// â�� ��ǰ�� ������ġ �˻� �� �����ϸ� taptstock.bin_no�� ����
        PartListBinListService partListBinListService = (PartListBinListService)CommonUtil.getBean("partListBinListService", user.getCompNo());
        // â�� ��ǰ�� ������ġ �˻��� ���� ���� ����
        PartListBinListDTO partListBinListDTO = new PartListBinListDTO();
        partListBinListDTO.setPartId(maPtRecDetailDTO.getPartId());
        partListBinListDTO.setWcodeId(maPtRecDetailDTO.getWcodeId());
        partListBinListDTO.setPartGrade(maPtRecDetailDTO.getPartGrade());

        // â�� ��ǰ�� ������ġ �˻�
        List ptWhBinList = partListBinListService.findPtWhBinList(partListBinListDTO, user);
       
        // ����� �����ϸ� taptstock.bin_no�� ����
        if(ptWhBinList.size() != 0)
        {
        	PartListBinDetailService partListBinDetailService = (PartListBinDetailService)CommonUtil.getBean("partListBinDetailService", user.getCompNo());
        	
        	for (int i = 0; i < ptWhBinList.size(); i++)
        	{
        		Map map = (Map)ptWhBinList.get(i);
        		PartListBinDetailDTO ptListBinDetailDTO = (PartListBinDetailDTO) CommonUtil.makeDTO(map, PartListBinDetailDTO.class);
        		
    			// ������ġ �� ��ȸ�� ���� â�� ��ǰ�� ������ġID ����
    			partListBinListDTO.setPtwhBinNoId(ptListBinDetailDTO.getPtwhBinNoId());
    			
    			// ������ġ ��ȸ(��)
    			PartListBinDetailDTO partListBinDetailDTO = partListBinDetailService.findPtWhBinDetail(partListBinListDTO, user);

    			// ������ġ(bin_no) ������Ʈ
    			partListBinDetailService.updateBinNo(partListBinDetailDTO, user);
        	}
        }
    }
    
}
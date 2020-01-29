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
 * 구매입고 - 상세 serviceimpl 
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
        
        //발주품목에서 가져온 데이터면 gr수량, 상태 업데이트
        if(!CommonUtil.isNullCheck(maPtRecDetailDTO.getPoitemId())){
            //gr수량
            PartPurPoItemService partPurPoItemService = (PartPurPoItemService) CommonUtil.getBean("partPurPoItemService", user);
            PartPurPoItemDTO partPurPoItemDTO = new PartPurPoItemDTO();
            partPurPoItemDTO.setPtPoItemId(maPtRecDetailDTO.getPoitemId());
            partPurPoItemDTO = partPurPoItemService.findDetail(partPurPoItemDTO, user);
            partPurPoItemDTO = partPurPoItemService.getQty(partPurPoItemDTO, user);
            partPurPoItemService.updateDetail(partPurPoItemDTO, user);
            
            //상태
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
        
        // 상태가 입고완료[C]가 아닌 경우만 Update 한다. 
        String preRecListStatus = maPtRecDetailDAO.findPrRecListStatus(compNo, prRecListId);
        if(!"C".equals(preRecListStatus))
        {
            resultCnt = maPtRecDetailDAO.updateDetail(maPtRecDetailDTO,user);
            
            //발주품목에서 가져온 데이터면 gr수량, 상태 업데이트
            if(!CommonUtil.isNullCheck(maPtRecDetailDTO.getPoitemId())){
                //gr수량
                PartPurPoItemService partPurPoItemService = (PartPurPoItemService) CommonUtil.getBean("partPurPoItemService", user);
                PartPurPoItemDTO partPurPoItemDTO = new PartPurPoItemDTO();
                partPurPoItemDTO.setPtPoItemId(maPtRecDetailDTO.getPoitemId());
                partPurPoItemDTO = partPurPoItemService.findDetail(partPurPoItemDTO, user);
                partPurPoItemDTO = partPurPoItemService.getQty(partPurPoItemDTO, user);
                partPurPoItemService.updateDetail(partPurPoItemDTO, user);
                
                //상태
                MaPtPoDetailService maPtPoDetailService = (MaPtPoDetailService) CommonUtil.getBean("maPtPoDetailService", user);
                MaPtPoDetailDTO maPtPoDetailDTO = maPtPoDetailService.findDetail(partPurPoItemDTO.getPoListId(), user);
                maPtPoDetailDTO = maPtPoDetailService.getStatus(maPtPoDetailDTO, user);
                maPtPoDetailService.updateDetail(maPtPoDetailDTO, user);
            }
        }
        
//        String isSerial = maPtRecDetailDTO.getIsSerial();
        String isSerial = "N";
        //시리얼이 아닌경우 시리얼 테이블 삭제
        if(!"Y".equals(isSerial))
        {
            maPtRecDetailDAO.deleteSerial(maPtRecDetailDTO);
        }
        //시리얼인경우 자재번호를 serial 테이블로 다시 저장
        else
        {
            maPtRecDetailDAO.updateSerialPart(maPtRecDetailDTO);
        }
        
        return resultCnt;
    }
    
    public int updatePtRecListStatus(MaPtRecDetailDTO maPtRecDetailDTO, User user) throws Exception
    {   
        int result = 0;
        //품번생성
        result += makePtMstr(maPtRecDetailDTO,user);
        //입고확정
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
        // 입고완료으로 상태를 변경할 경우 아래 프로시져를 호출한다. 
        int result = 0;
        if(!"".equals(maPtRecDetailDTO.getPartId()))
        {
        	// 이력 테이블 id를 생성후, 이력데이터 생성 및 프로시져 호출. 
        	String ptRecHistId = maPtRecDetailDAO.getNextSequence("SQAPTRECHIST_ID");
        	maPtRecDetailDAO.insertRecHistory(ptRecHistId, maPtRecDetailDTO, user);
        	maPtRecDetailDAO.executeSpPtInstock(user.getCompNo(), ptRecHistId);
        }

        result+=maPtRecDetailDAO.updatePtRecListStatus(maPtRecDetailDTO, user);            
        
        //발주품목에서 가져온 데이터면 gr수량, 상태 업데이트
        if(!CommonUtil.isNullCheck(maPtRecDetailDTO.getPoitemId())){
            //gr수량
            PartPurPoItemService partPurPoItemService = (PartPurPoItemService) CommonUtil.getBean("partPurPoItemService", user);
            PartPurPoItemDTO partPurPoItemDTO = new PartPurPoItemDTO();
            partPurPoItemDTO.setPtPoItemId(maPtRecDetailDTO.getPoitemId());
            partPurPoItemDTO = partPurPoItemService.findDetail(partPurPoItemDTO, user);
            partPurPoItemDTO = partPurPoItemService.getQty(partPurPoItemDTO, user);
            partPurPoItemService.updateDetail(partPurPoItemDTO, user);
            
            //상태
            MaPtPoDetailService maPtPoDetailService = (MaPtPoDetailService) CommonUtil.getBean("maPtPoDetailService", user);
            MaPtPoDetailDTO maPtPoDetailDTO = maPtPoDetailService.findDetail(partPurPoItemDTO.getPoListId(), user);
            maPtPoDetailDTO = maPtPoDetailService.getStatus(maPtPoDetailDTO, user);
            maPtPoDetailService.updateDetail(maPtPoDetailDTO, user);
        }
        
        // 구입처(부품마스터) 업데이트
        updatePartsVendor(maPtRecDetailDTO, user);
        
        // 현재고 보관위치 갱신 
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
            
            // List 수만큼 반복한다.
            for (int i=0; i<pageMenuSize; i++)
            {
                tempMap = (Map)serialList.get(i);
                
                // 해당 row map 의 user_group 과 object_type 을 추출한다.
                String prreclistSerialId = String.valueOf(tempMap.get("PRRECLISTSERIALID"));
                String partId = String.valueOf(tempMap.get("PARTID"));
                String serialNo     = (String)tempMap.get("SERIALNO");   
                String isEquipId     = String.valueOf(tempMap.get("EQUIPID"));  
                
                //EqupId 가 비어있지 않다면 이전에 이미 확정을 해서 설비를 생성한 상태임.
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
        //시리얼 자재를 입고확정 취소할경우 설비를 
        else if("R".equals(maPtRecDetailDTO.getPtRecMode())&&"Y".equals(maPtRecDetailDTO.getIsSerial()))
        {
            List serialList = maPtRecDetailDAO.findSerialList(maPtRecDetailDTO);
            
            Hashtable menuPathTable = new Hashtable();
            
            int pageMenuSize = serialList.size();

            Map tempMap = null;
            
            // List 수만큼 반복한다.
            for (int i=0; i<pageMenuSize; i++)
            {
                tempMap = (Map)serialList.get(i);
                
                // 해당 row map 의 user_group 과 object_type 을 추출한다.
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
        // 품번생성여부가 Y인 경우 품명과 규격이 TAPARTS에 존재하는지 확인한다.
        int result = 0 ;
        if("Y".equals(maPtRecDetailDTO.getIsMakePartNoId()))
        {
            String existPartId ="";
            // 존재하지 않을 경우 0 return
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
            // 업데이트하여 part_id를 넣어준다.
            result+=maPtRecDetailDAO.updatePartNo(maPtRecDetailDTO, user);
        }
        return result;
    }

    // 공급업체(구입처) 업데이트
    public void updatePartsVendor(MaPtRecDetailDTO maPtRecDetailDTO, User user) throws Exception
    {
    	MaPtMstrDetailService maPtMstrDetailService = (MaPtMstrDetailService) CommonUtil.getBean("maPtMstrDetailService");
    	
    	//update 전 기존 부품 데이터 find
    	MaPtMstrCommonDTO maPtMstrCommonDTO = new MaPtMstrCommonDTO();
    	maPtMstrCommonDTO.setPartId(maPtRecDetailDTO.getPartId());
    	
    	MaPtMstrDetailDTO maPtMstrDetailDTO = maPtMstrDetailService.findDetail(maPtMstrCommonDTO, user);
    	
    	// 부품 상세 정보 갱신(구입처 갱신)
    	// 입고거래처가 비어있지 않으면 구입처 갱신
    	if(!"".equals(maPtRecDetailDTO.getVendorId()) && null != maPtRecDetailDTO.getVendorId())
    	{
    		maPtMstrDetailDTO.setSeller(maPtRecDetailDTO.getVendorDesc());
    	}

    	maPtMstrDetailDTO.setCompNo(user.getCompNo());
    	
    	maPtMstrDetailService.updateDetail(maPtMstrDetailDTO, user);
    }
    
    // 보관위치(bin_no) 업데이트
    public void updateBinNo(MaPtRecDetailDTO maPtRecDetailDTO, User user) throws Exception
    {
    	// 창고별 부품별 저장위치 검색 후 존재하면 taptstock.bin_no를 갱신
        PartListBinListService partListBinListService = (PartListBinListService)CommonUtil.getBean("partListBinListService", user.getCompNo());
        // 창고별 부품별 저장위치 검색을 위한 조건 셋팅
        PartListBinListDTO partListBinListDTO = new PartListBinListDTO();
        partListBinListDTO.setPartId(maPtRecDetailDTO.getPartId());
        partListBinListDTO.setWcodeId(maPtRecDetailDTO.getWcodeId());
        partListBinListDTO.setPartGrade(maPtRecDetailDTO.getPartGrade());

        // 창고별 부품별 저장위치 검색
        List ptWhBinList = partListBinListService.findPtWhBinList(partListBinListDTO, user);
       
        // 결과가 존재하면 taptstock.bin_no를 갱신
        if(ptWhBinList.size() != 0)
        {
        	PartListBinDetailService partListBinDetailService = (PartListBinDetailService)CommonUtil.getBean("partListBinDetailService", user.getCompNo());
        	
        	for (int i = 0; i < ptWhBinList.size(); i++)
        	{
        		Map map = (Map)ptWhBinList.get(i);
        		PartListBinDetailDTO ptListBinDetailDTO = (PartListBinDetailDTO) CommonUtil.makeDTO(map, PartListBinDetailDTO.class);
        		
    			// 보관위치 상세 조회를 위해 창고별 부품별 저장위치ID 셋팅
    			partListBinListDTO.setPtwhBinNoId(ptListBinDetailDTO.getPtwhBinNoId());
    			
    			// 보관위치 조회(상세)
    			PartListBinDetailDTO partListBinDetailDTO = partListBinDetailService.findPtWhBinDetail(partListBinListDTO, user);

    			// 보관위치(bin_no) 업데이트
    			partListBinDetailService.updateBinNo(partListBinDetailDTO, user);
        	}
        }
    }
    
}
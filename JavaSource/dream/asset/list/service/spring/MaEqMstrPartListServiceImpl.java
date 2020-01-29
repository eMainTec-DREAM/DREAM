package dream.asset.list.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import common.util.StringUtil;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgPartDetailDTO;
import dream.asset.categ.list.dto.MaEqCtgPartListDTO;
import dream.asset.categ.list.service.MaEqCtgPartDetailService;
import dream.asset.categ.list.service.MaEqCtgPartListService;
import dream.asset.list.dao.MaEqMstrPartListDAO;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.dto.MaEqMstrPartDetailDTO;
import dream.asset.list.dto.MaEqMstrPartListDTO;
import dream.asset.list.service.MaEqMstrAsmbListService;
import dream.asset.list.service.MaEqMstrPartDetailService;
import dream.asset.list.service.MaEqMstrPartListService;

/**
 * 구성자재 목록
 * @author kim21017
 * @version $Id: MaEqMstrPartListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maEqMstrPartListServiceTarget"
 * @spring.txbn id="maEqMstrPartListService"
 * @spring.property name="maEqMstrPartListDAO" ref="maEqMstrPartListDAO"
 * @spring.property name="maEqMstrPartDetailService" ref="maEqMstrPartDetailService"
 */
public class MaEqMstrPartListServiceImpl implements MaEqMstrPartListService
{
    private MaEqMstrPartListDAO maEqMstrPartListDAO = null;
    private MaEqMstrPartDetailService maEqMstrPartDetailService = null;


	public MaEqMstrPartDetailService getMaEqMstrPartDetailService()
    {
        return maEqMstrPartDetailService;
    }

    public void setMaEqMstrPartDetailService(
            MaEqMstrPartDetailService maEqMstrPartDetailService)
    {
        this.maEqMstrPartDetailService = maEqMstrPartDetailService;
    }

    public List findPartList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPartListDTO maEqMstrPartListDTO, User loginUser)
    {      
        return maEqMstrPartListDAO.findPartList(maEqMstrCommonDTO, maEqMstrPartListDTO, loginUser);
    }
	
	public MaEqMstrPartListDAO getMaEqMstrPartListDAO() {
		return maEqMstrPartListDAO;
	}

	public void setMaEqMstrPartListDAO(MaEqMstrPartListDAO maEqMstrPartListDAO) {
		this.maEqMstrPartListDAO = maEqMstrPartListDAO;
	}
	
	public int deletePartList(String[] deleteRows, String compNo) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maEqMstrPartListDAO.deletePartList(id, compNo);
            }
        
        return result;
    }
	public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPartListDTO maEqMstrPartListDTO, User user) throws Exception
    {      
        return maEqMstrPartListDAO.findTotalCount(maEqMstrCommonDTO, maEqMstrPartListDTO,user);
    }

    @Override
    public int inputPartList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPartDetailDTO maEqMtrPartDetailDTO, User user) throws Exception
    {
        int result = 0;
        
        String[] multiKey = maEqMtrPartDetailDTO.getMultiKey().split("\\+");
        
        for(int i=0; multiKey.length > i; i++)
        {
        	maEqMtrPartDetailDTO.setPartId(multiKey[i]);
        	String cnt = maEqMstrPartListDAO.validPart(maEqMstrCommonDTO.getEquipId(), maEqMtrPartDetailDTO.getPartId(), user);
            
            if("0".equals(cnt))
            {
	            maEqMtrPartDetailDTO.setEqPartId(maEqMstrPartListDAO.getNextSequence("SQAEQPART_ID"));
	            result = result + maEqMstrPartDetailService.insertDetail(maEqMtrPartDetailDTO, maEqMstrCommonDTO, user);
            }
        }
        
        return result;
    }
    
    @Override
    public int inputEqCtgPart(MaEqCtgPartDetailDTO maEqCtgPartDetailDTO, String equipId, User user) throws Exception
    {
        int result = 0;
        
        MaEqMstrAsmbListService maEqMstrAsmbListService = (MaEqMstrAsmbListService) CommonUtil.getBean("maEqMstrAsmbListService", user);
        
        //validation
        try{
            if(Integer.parseInt(equipId)<=0) {
                throw new NumberFormatException();
            }
        }catch(NumberFormatException nfe){
            nfe.printStackTrace();
            return result;
        }
        
        String eqPartId = this.getEqPart(equipId, maEqCtgPartDetailDTO.getEqCtgPartId(), user);
        String eqAsmbId = "";
        if(!"".equals(maEqCtgPartDetailDTO.getEqCtgAsmbId()) && !"0".equals(maEqCtgPartDetailDTO.getEqCtgAsmbId())){
            eqAsmbId = maEqMstrAsmbListService.getEqAsmb(equipId, maEqCtgPartDetailDTO.getEqCtgAsmbId(), user);
        }
        
        if(eqPartId == null || "".equals(eqPartId))
        {
            MaEqMstrPartDetailDTO maEqMstrPartDetailDTO = new MaEqMstrPartDetailDTO();
            maEqMstrPartDetailDTO.setEquipId(equipId);
            maEqMstrPartDetailDTO.setEqPartId(maEqMstrPartListDAO.getNextSequence("SQAEQPART_ID"));
            maEqMstrPartDetailDTO.setEqAsmbId(eqAsmbId);
            maEqMstrPartDetailDTO.setEqCtgPartId(maEqCtgPartDetailDTO.getEqCtgPartId());
            maEqMstrPartDetailDTO.setPartId(maEqCtgPartDetailDTO.getPartId());
            maEqMstrPartDetailDTO.setConsistQty(maEqCtgPartDetailDTO.getUseQty());
            maEqMstrPartDetailDTO.setOrdNo(maEqCtgPartDetailDTO.getOrdNo());
            maEqMstrPartDetailDTO.setIsUse(maEqCtgPartDetailDTO.getIsUse());
            maEqMstrPartDetailDTO.setIsEqTypePart(maEqCtgPartDetailDTO.getIsEqTypePart());
            result += maEqMstrPartDetailService.insertDetail(maEqMstrPartDetailDTO, new MaEqMstrCommonDTO(), user);
        }
        else
        {
            MaEqMstrPartListDTO maEqMstrPartListDTO = new MaEqMstrPartListDTO();
            maEqMstrPartListDTO.setEqPartId(eqPartId);
            MaEqMstrPartDetailDTO maEqMstrPartDetailDTO = maEqMstrPartDetailService.findDetail(maEqMstrPartListDTO, user);
            maEqMstrPartDetailDTO.setEqAsmbId(eqAsmbId);
            maEqMstrPartDetailDTO.setEqCtgPartId(maEqCtgPartDetailDTO.getEqCtgPartId());
            maEqMstrPartDetailDTO.setPartId(maEqCtgPartDetailDTO.getPartId());
            maEqMstrPartDetailDTO.setConsistQty(maEqCtgPartDetailDTO.getUseQty());
            maEqMstrPartDetailDTO.setOrdNo(maEqCtgPartDetailDTO.getOrdNo());
            maEqMstrPartDetailDTO.setIsUse(maEqCtgPartDetailDTO.getIsUse());
            maEqMstrPartDetailDTO.setIsEqTypePart(maEqCtgPartDetailDTO.getIsEqTypePart());
            result += maEqMstrPartDetailService.updateDetail(maEqMstrPartDetailDTO, new MaEqMstrCommonDTO(), user);
        }
        
        return result;
    }
    
    @Override
    public int inputEqCtgPartList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrPartListDTO maEqMstrPartListDTO, User user) throws Exception
    {
        MaEqCtgPartDetailService maEqCtgPartDetailService = (MaEqCtgPartDetailService) CommonUtil.getBean("maEqCtgPartDetailService", user);
        
        int result = 0;
        
        String equipId = maEqMstrCommonDTO.getEquipId();
        String[] eqCtgPartIds = maEqMstrPartListDTO.getEqCtgPartIds().split("\\+");
        
        for(String eqCtgPartId:eqCtgPartIds) {
            MaEqCtgPartListDTO maEqCtgPartListDTO = new MaEqCtgPartListDTO();
            maEqCtgPartListDTO.setEqCtgPartId(eqCtgPartId);
            MaEqCtgPartDetailDTO maEqCtgPartDetailDTO = maEqCtgPartDetailService.findDetail(maEqCtgPartListDTO, user);
            
            result += this.inputEqCtgPart(maEqCtgPartDetailDTO, equipId, user);
        }
        
        return result;
    }
    
    @Override
    public int inputEqCtgPartList(String eqCtgId, String equipId, User user) throws Exception
    {
        MaEqCtgPartListService maEqCtgPartListService = (MaEqCtgPartListService) CommonUtil.getBean("maEqCtgPartListService", user);
        
        int result = 0;
        
        //validation
        try{
            if(Integer.parseInt(equipId)<=0 || Integer.parseInt(eqCtgId)<=0) {
                throw new NumberFormatException();
            }
        }catch(NumberFormatException nfe){
            nfe.printStackTrace();
            return result;
        }
        
        // 유사설비공통부품여부 N
        this.unlinkOldEqPart(equipId, user);
        // 변경된 설비종류 표준 부품 추가
        MaEqCatalogCommonDTO maEqCatalogCommonDTO = new MaEqCatalogCommonDTO();
        MaEqCtgPartListDTO maEqCtgPartListDTO = new MaEqCtgPartListDTO();
        maEqCatalogCommonDTO.setEqCtgId(eqCtgId);
        List<Map> eqCtgPartList = maEqCtgPartListService.findPartList(maEqCatalogCommonDTO, maEqCtgPartListDTO, user);
        
        for(Map eqCtgPart:eqCtgPartList){
            MaEqCtgPartDetailDTO maEqCtgPartDetailDTO = (MaEqCtgPartDetailDTO) CommonUtil.makeDTO(eqCtgPart, MaEqCtgPartDetailDTO.class);
            
            if("Y".equals(maEqCtgPartDetailDTO.getIsEqTypePart())) {
                result += this.inputEqCtgPart(maEqCtgPartDetailDTO, equipId, user);
            }
        }
        
        return result;
    }
    
    private String getEqPart(String equipId, String eqCtgPartId, User user)
    {
        String eqPartId = "";
        
        //validation
        try{
            if(Integer.parseInt(equipId)<=0 || Integer.parseInt(eqCtgPartId)<=0){
                throw new NumberFormatException();
            }
        }catch(NumberFormatException nfe){
            nfe.printStackTrace();
            return eqPartId;
        }
        
        MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
        maEqMstrCommonDTO.setEquipId(equipId);
        MaEqMstrPartListDTO maEqMstrPartListDTO = new MaEqMstrPartListDTO();
        maEqMstrPartListDTO.setEqCtgPartId(eqCtgPartId);
        List<Map> eqPartList = maEqMstrPartListDAO.findPartList(maEqMstrCommonDTO, maEqMstrPartListDTO, user);
        if(eqPartList.size()>0) eqPartId = StringUtil.valueOf(eqPartList.get(0).get("EQPARTID"));
        
        return eqPartId;
    }
    
    @Override
    public int unlinkOldEqPart(String equipId, User user) throws Exception
    {
        int result = 0;
        
        //validation
        try{
            if(Integer.parseInt(equipId)<=0){
                throw new NumberFormatException();
            }
        }catch(NumberFormatException nfe){
            nfe.printStackTrace();
            return result;
        }
        
        MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
        maEqMstrCommonDTO.setEquipId(equipId);
        List<Map> eqPartList = maEqMstrPartListDAO.findPartList(maEqMstrCommonDTO, new MaEqMstrPartListDTO(), user);
        
        for(Map eqPart:eqPartList){
            MaEqMstrPartDetailDTO maEqMstrPartDetailDTO = (MaEqMstrPartDetailDTO) CommonUtil.makeDTO(eqPart, MaEqMstrPartDetailDTO.class);
            maEqMstrPartDetailDTO.setIsEqTypePart("N");
            result += maEqMstrPartDetailService.updateDetail(maEqMstrPartDetailDTO, maEqMstrCommonDTO, user);
        }
        
        return result;
    }
    
    @Override
    public int undoEqCtgPart(String eqCtgPartId, User user) throws Exception
    {
        int result = 0;
        
        //validation
        try{
            if(Integer.parseInt(eqCtgPartId)<=0){
                throw new NumberFormatException();
            }
        }catch(NumberFormatException nfe){
            nfe.printStackTrace();
            return result;
        }
        
        MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
        MaEqMstrPartListDTO maEqMstrPartListDTO = new MaEqMstrPartListDTO();
        maEqMstrPartListDTO.setEqCtgPartId(eqCtgPartId);
        List<Map> eqPartList = maEqMstrPartListDAO.findPartList(maEqMstrCommonDTO, maEqMstrPartListDTO, user);
        
        for(Map eqPart:eqPartList){
            MaEqMstrPartDetailDTO maEqMstrPartDetailDTO = (MaEqMstrPartDetailDTO) CommonUtil.makeDTO(eqPart, MaEqMstrPartDetailDTO.class);
            maEqMstrPartDetailDTO.setIsEqTypePart("N");
            result += maEqMstrPartDetailService.updateDetail(maEqMstrPartDetailDTO, maEqMstrCommonDTO, user);
        }
        
        return result;
    }
    
	@Override
	public void saveList(List<Map> gridList, User user) throws Exception 
	{
		MaEqMstrCommonDTO maEqMstrCommonDTO = null;
		MaEqMstrPartDetailDTO maEqMstrPartDetailDTO = null;
		
		for(Map map : gridList)
        {
			maEqMstrCommonDTO = (MaEqMstrCommonDTO)CommonUtil.makeDTO(map, MaEqMstrCommonDTO.class);
        	maEqMstrPartDetailDTO = (MaEqMstrPartDetailDTO)CommonUtil.makeDTO(map, MaEqMstrPartDetailDTO.class);
        	
        	maEqMstrCommonDTO.setCompNo(user.getCompNo());
        	
        	switch(maEqMstrPartDetailDTO.getNativeeditor())
        	{
        		case "inserted":
        			break;
        		case "updated" : maEqMstrPartDetailService.updateDetail(maEqMstrPartDetailDTO,maEqMstrCommonDTO, user);
        			break;
        		case "deleted": 
        			break;
        	}
        	
        }
	}
}


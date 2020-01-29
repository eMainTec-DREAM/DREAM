package dream.part.list.service.spring;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.struts.upload.FormFile;

import common.bean.User;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.MessageUtil;
import dream.doc.img.dto.MaDocImgCommonDTO;
import dream.doc.img.dto.MaDocImgDetailDTO;
import dream.doc.img.service.MaDocImgDetailService;
import dream.part.list.dao.PartListImgLinkUrlDAO;
import dream.part.list.dto.PartListImgLinkUrlDTO;
import dream.part.list.service.PartListImgLinkUrlService;

/**
 * 부품Image Link Service implements
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="partListImgLinkUrlServiceTarget"
 * @spring.txbn id="partListImgLinkUrlService"
 * @spring.property name="partListImgLinkUrlDAO" ref="partListImgLinkUrlDAO"
 */
public class PartListImgLinkUrlServiceImpl implements PartListImgLinkUrlService
{
	private PartListImgLinkUrlDAO partListImgLinkUrlDAO = null;
	
	public PartListImgLinkUrlDAO getPartListImgLinkUrlDAO() {
	    return partListImgLinkUrlDAO;
	}
	
	public void setPartListImgLinkUrlDAO(PartListImgLinkUrlDAO partListImgLinkUrlDAO) {
	    this.partListImgLinkUrlDAO = partListImgLinkUrlDAO;
	}

	public List findList(PartListImgLinkUrlDTO partListImgLinkUrlDTO, User user) throws Exception
    {      
        return partListImgLinkUrlDAO.find(partListImgLinkUrlDTO,user);
    }
	
	public String findTotalCount(PartListImgLinkUrlDTO partListImgLinkUrlDTO,User user) throws Exception
	{
	    return partListImgLinkUrlDAO.findTotalCount(partListImgLinkUrlDTO, user);
	}

	public int deleteList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;
        
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                // 보관위치 정보 삭제
                result = result + partListImgLinkUrlDAO.delete(id, user);
            }
        
        return result;
    }
	
	public PartListImgLinkUrlDTO findDetail(PartListImgLinkUrlDTO partListImgLinkUrlDTO, User user) throws Exception
	{
	    return (PartListImgLinkUrlDTO) CommonUtil.makeDetailFromList(this.findList(partListImgLinkUrlDTO, user), partListImgLinkUrlDTO);
	}
    
    public int insertDetail(PartListImgLinkUrlDTO partListImgLinkUrlDTO, User user) throws Exception
    {
        int result = 0;
        
        partListImgLinkUrlDTO.setIsReceived("N");
        partListImgLinkUrlDTO.setImageReceiveStatus("B");
        partListImgLinkUrlDTO.setCreDate(DateUtil.getTimeStamp(user.getOffset(), "yyyyMMddHHmmss"));
        partListImgLinkUrlDTO.setCreBy(user.getUserId());
        partListImgLinkUrlDTO.setUpdDate(DateUtil.getTimeStamp(user.getOffset(), "yyyyMMddHHmmss"));
        partListImgLinkUrlDTO.setUpdBy(user.getUserId());
        result = partListImgLinkUrlDAO.insert(partListImgLinkUrlDTO, user);
        
        return result;
    }
    
    public int updateDetail(PartListImgLinkUrlDTO partListImgLinkUrlDTO, User user) throws Exception
    {
        int result = 0;
        
        partListImgLinkUrlDTO.setIsReceived("N");
        partListImgLinkUrlDTO.setImageReceiveStatus("B");
        partListImgLinkUrlDTO.setReceivedTime("");
        partListImgLinkUrlDTO.setUpdDate(DateUtil.getTimeStamp(user.getOffset(), "yyyyMMddHHmmss"));
        partListImgLinkUrlDTO.setUpdBy(user.getUserId());
        result = partListImgLinkUrlDAO.update(partListImgLinkUrlDTO, user);
        
        return result;
    }
    
    @Override
    public Map<String, String> getImage(PartListImgLinkUrlDTO partListImgLinkUrlDTO, User user) throws Exception
    {
        Map<String, String> rstMap = new HashMap<String, String>();
        partListImgLinkUrlDTO = this.findDetail(partListImgLinkUrlDTO, user);
        if("Y".equals(partListImgLinkUrlDTO.getIsReceived())){
            rstMap.put("state", "ERROR");
            rstMap.put("info", MessageUtil.getMessage(new Locale(user.getLangId()), "MESSAGE", "MSG0296"));
            
            return rstMap;
        }
        MaDocImgDetailService maDocImgDetailService = (MaDocImgDetailService) CommonUtil.getBean("maDocImgDetailService", user);
        
        MaDocImgDetailDTO maDocImgDetailDTO = new MaDocImgDetailDTO();
        maDocImgDetailDTO.setCompNo(user.getCompNo());
        maDocImgDetailDTO.setImageId(partListImgLinkUrlDAO.getNextSequence("SQAIMAGE_ID"));
        String url = partListImgLinkUrlDTO.getImgUrl();
        String fileName = url.substring(url.lastIndexOf("/")+1, url.length());
        maDocImgDetailDTO.setDescription(fileName);
        maDocImgDetailDTO.setObjectId(partListImgLinkUrlDTO.getPartId());
        maDocImgDetailDTO.setObjectType("PTMSTR");
        maDocImgDetailDTO.setDeptId(user.getDeptId());
        maDocImgDetailDTO.setRegId(user.getEmpId());
        maDocImgDetailDTO.setRegDate(DateUtil.getTimeStamp(user.getOffset()));
        rstMap = maDocImgDetailService.uploadAutoFiles(maDocImgDetailDTO, partListImgLinkUrlDTO.getImgUrl());
        
        if(rstMap.containsValue("ERROR")) {
            partListImgLinkUrlDTO.setImageReceiveStatus("F");
        }
        else {
            maDocImgDetailService.insertDetail(maDocImgDetailDTO, new MaDocImgCommonDTO(), new FormFile[500]);
            rstMap.put("info", MessageUtil.getMessage(new Locale(user.getLangId()), "MESSAGE", "MGS0235"));
            partListImgLinkUrlDTO.setImageReceiveStatus("S");
        }
        partListImgLinkUrlDTO.setIsReceived("Y");
        partListImgLinkUrlDTO.setReceivedTime(DateUtil.getTimeStamp(user.getOffset(), "yyyyMMddHHmmss"));
        partListImgLinkUrlDAO.update(partListImgLinkUrlDTO, user);
        
        return rstMap;
    }
    
    @Override
    public void getImageAll(User user) throws Exception
    {
        PartListImgLinkUrlDTO partListImgLinkUrlDTO = new PartListImgLinkUrlDTO();
        partListImgLinkUrlDTO.setImageReceiveStatus("B");
        List<Map> list = this.findList(partListImgLinkUrlDTO, user);
        for(Map map:list){
            partListImgLinkUrlDTO = (PartListImgLinkUrlDTO) CommonUtil.makeDTO(map, PartListImgLinkUrlDTO.class);
            this.getImage(partListImgLinkUrlDTO, user);
        }
    }
}


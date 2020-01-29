package dream.doc.ctg.service.spring;

import java.util.ArrayList;
import java.util.List;

import common.bean.User;
import common.util.CommonUtil;
import dream.doc.ctg.dao.DocCtgDetailDAO;
import dream.doc.ctg.dto.DocCtgCommonDTO;
import dream.doc.ctg.dto.DocCtgDetailDTO;
import dream.doc.ctg.service.DocCtgDetailService;
import dream.doc.ctg.service.DocCtgListService;

/**
 * 문서분류체계 - 상세 serviceimpl 
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="docCtgDetailServiceTarget"
 * @spring.txbn id="docCtgDetailService"
 * @spring.property name="docCtgDetailDAO" ref="docCtgDetailDAO"
 */
public class DocCtgDetailServiceImpl implements DocCtgDetailService
{
    private DocCtgDetailDAO docCtgDetailDAO = null;
    
    public DocCtgDetailDAO getDocCtgDetailDAO() 
    {
		return docCtgDetailDAO;
	}

	public void setDocCtgDetailDAO(DocCtgDetailDAO docCtgDetailDAO) 
	{
		this.docCtgDetailDAO = docCtgDetailDAO;
	}

	public DocCtgDetailDTO findDetail(String docCtgId,User user)throws Exception
    {
        DocCtgDetailDTO docCtgDetailDTO = docCtgDetailDAO.findDetail(docCtgId, user);
        
        return docCtgDetailDTO;
    }
	
	public int insertDetail(DocCtgDetailDTO docCtgDetailDTO, User loginUser) throws Exception
    {   	
	   int rtnValue = 0;
	    
	   rtnValue = 
	   docCtgDetailDAO.insertDetail(docCtgDetailDTO);
	   
	   //full_desc를 update
	   this.updateFullDesc(docCtgDetailDTO.getDocctgId(), loginUser);
	    		
       return rtnValue;

    }
	
	public int updateDetail(DocCtgDetailDTO docCtgDetailDTO, User loginUser) throws Exception
    {        
	    int rtnValue = 0;
	    
		DocCtgDetailDTO originDTO = this.findDetail(docCtgDetailDTO.getDocctgId(), loginUser);
		
		List list = new ArrayList();
	    list.add(docCtgDetailDTO);
	    rtnValue = docCtgDetailDAO.updateDetail(list, loginUser)[0];
		
	    System.out.println("originDTO.getDescription() : " + originDTO.getDescription());
	    System.out.println("docCtgDetailDTO.getDescription() : " + docCtgDetailDTO.getDescription());
	    
	    //description이 변경되었거나 부모가 변경되었을 경우 full_desc를 update
        if(!docCtgDetailDTO.getDescription().equals(originDTO.getDescription())
                || !docCtgDetailDTO.getPdocctgId().equals(originDTO.getPdocctgId())){
            this.updateFullDesc(docCtgDetailDTO.getDocctgId(), loginUser);
        }
        
        return rtnValue;
    }
	
	public int updateFullDesc(String docCtgid, User user) throws Exception 
	{
		int rtnValue = 0;
        
		DocCtgListService docCtgListService = (DocCtgListService) CommonUtil.getBean("docCtgListService", user);
        List list = CommonUtil.makeFullDesc(docCtgListService.findList(new DocCtgCommonDTO(), user), docCtgid, "DOCCTGID", "PDOCCTGID", "DESCRIPTION", "FULLDESC", DocCtgDetailDTO.class);
        
        int[] arr = docCtgDetailDAO.updateDetail(list, user);
        
        return rtnValue;
	}
	
	public int updateFullDesc(User user) throws Exception 
	{
		int rtnValue = 0;
		
		DocCtgListService docCtgListService = (DocCtgListService) CommonUtil.getBean("docCtgListService", user);
		List list = CommonUtil.makeFullDesc(docCtgListService.findList(new DocCtgCommonDTO(), user), "0", "DOCCTGID", "PDOCCTGID", "DESCRIPTION", "FULLDESC", DocCtgDetailDTO.class);
		
		int[] arr = docCtgDetailDAO.updateDetail(list, user);
		
		return rtnValue;
	}
}

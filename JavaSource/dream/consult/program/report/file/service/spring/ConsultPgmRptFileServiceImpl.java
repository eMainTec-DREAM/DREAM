package dream.consult.program.report.file.service.spring;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import common.bean.User;
import common.util.CommonUtil;
import dream.consult.program.report.dto.ConsultPgmRptDTO;
import dream.consult.program.report.file.dao.ConsultPgmRptFileDAO;
import dream.consult.program.report.file.dto.ConsultPgmRptFileDTO;
import dream.consult.program.report.file.service.ConsultPgmRptFileService;

/**
 * 출력물 설정 파일 - 목록 serviceimpl 
 * @author youngjoo38
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="consultPgmRptFileServiceTarget"
 * @spring.txbn id="consultPgmRptFileService"
 * @spring.property name="consultPgmRptFileDAO" ref="consultPgmRptFileDAO"
 */
public class ConsultPgmRptFileServiceImpl implements ConsultPgmRptFileService
{
    private ConsultPgmRptFileDAO consultPgmRptFileDAO = null;

    public ConsultPgmRptFileDAO getConsultPgmRptFileDAO() 
    {
        return consultPgmRptFileDAO;
    }

    public void setConsultPgmRptFileDAO(ConsultPgmRptFileDAO consultPgmRptFileDAO) 
    {
        this.consultPgmRptFileDAO = consultPgmRptFileDAO;
    }

    public List findList(ConsultPgmRptFileDTO consultPgmRptFileDTO, User user) throws Exception
    {      
        return consultPgmRptFileDAO.findList(consultPgmRptFileDTO, user);
    }
    
    public String findTotalCount(ConsultPgmRptFileDTO consultPgmRptFileDTO, User user)
    {      
    	return consultPgmRptFileDAO.findTotalCount(consultPgmRptFileDTO, user);
    }
    
	public int[] deleteList(String[] deleteRows, User user) throws Exception
    {
        int[] result = null;
        ConsultPgmRptFileDTO consultPgmRptFileDTO = new ConsultPgmRptFileDTO();
        List<ConsultPgmRptFileDTO> list = new ArrayList<ConsultPgmRptFileDTO>();
        
        if(!deleteRows.equals(null)) {
        	for(int i=0; i<deleteRows.length; i++) {
        		consultPgmRptFileDTO.setRptFileId(deleteRows[i]);
        		
        		list.add((ConsultPgmRptFileDTO)BeanUtils.cloneBean(consultPgmRptFileDTO));
        	}
        	result = consultPgmRptFileDAO.deleteList(list, user);
        }
        
        return result;
    }

	@Override
	public ConsultPgmRptFileDTO findDetail(ConsultPgmRptFileDTO consultPgmRptFileDTO, User user) throws Exception 
	{
		return (ConsultPgmRptFileDTO)CommonUtil.makeDetailFromList(consultPgmRptFileDAO.findList(consultPgmRptFileDTO, user), consultPgmRptFileDTO);
	}
	
	public int updateDetail(ConsultPgmRptFileDTO consultPgmRptFileDTO, User user) throws Exception
    {
		int[] result = null;
        
        List<ConsultPgmRptFileDTO> list = new ArrayList<ConsultPgmRptFileDTO>();
        list.add(consultPgmRptFileDTO);
        
        result = consultPgmRptFileDAO.updateDetail(list, user);
        
        return result[0];
    }
	
	public int insertDetail(ConsultPgmRptFileDTO consultPgmRptFileDTO, User user) throws Exception
	{
		if(CommonUtil.isNullCheck(consultPgmRptFileDTO.getRptFileId()))
			consultPgmRptFileDTO.setRptFileId(consultPgmRptFileDAO.getNextSequence("SQAALARMREQ_ID"));
		
		List<ConsultPgmRptFileDTO> list = new ArrayList<ConsultPgmRptFileDTO>();
		list.add(consultPgmRptFileDTO);
		
		int[] rtn =  this.insertDetail(list, user);
		
		return rtn[0];
	}

	public int[] insertDetail(List<ConsultPgmRptFileDTO> list, User user) throws Exception
    {
        return consultPgmRptFileDAO.insertDetail(list, user);
    }
	
	public String checkDetail(ConsultPgmRptFileDTO consultPgmRptFileDTO, User user) throws Exception
	{
		return consultPgmRptFileDAO.checkDetail(consultPgmRptFileDTO, user);
	}
}
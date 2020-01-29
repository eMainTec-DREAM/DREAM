package dream.work.alarm.service.spring;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;

import common.bean.User;
import common.util.CommonUtil;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.asset.list.service.MaEqMstrListService;
import dream.req.work.dao.MaWoReqDetailDAO;
import dream.req.work.dto.MaWoReqDetailDTO;
import dream.req.work.service.MaWoReqDetailService;
import dream.work.alarm.dao.WorkAlarmDAO;
import dream.work.alarm.dto.WorkAlarmDTO;
import dream.work.alarm.req.dto.WorkAlarmReqDTO;
import dream.work.alarm.req.service.WorkAlarmReqService;
import dream.work.alarm.service.WorkAlarmService;

/**
 * Alarm List - 목록 serviceimpl 
 * @author nhkim8548
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workAlarmServiceTarget"
 * @spring.txbn id="workAlarmService"
 * @spring.property name="workAlarmDAO" ref="workAlarmDAO"
 */
public class WorkAlarmServiceImpl implements WorkAlarmService
{
    private WorkAlarmDAO workAlarmDAO = null;

    public WorkAlarmDAO getWorkAlarmDAO() 
    {
        return workAlarmDAO;
    }

    public void setWorkAlarmDAO(WorkAlarmDAO workAlarmDAO) 
    {
        this.workAlarmDAO = workAlarmDAO;
    }

    public List findList(WorkAlarmDTO workAlarmDTO, User user) throws Exception
    {      
        return workAlarmDAO.findList(workAlarmDTO, user);
    }
    
    public String findTotalCount(WorkAlarmDTO workAlarmDTO, User user)
    {      
    	return workAlarmDAO.findTotalCount(workAlarmDTO, user);
    }

	public int[] deleteList(String[] deleteRows, User user) throws Exception
    {
        int[] result = null;
        WorkAlarmDTO workAlarmDTO = new WorkAlarmDTO();
        List<WorkAlarmDTO> list = new ArrayList<WorkAlarmDTO>();
        
        if(!deleteRows.equals(null)) {
        	for(int i=0; i<deleteRows.length; i++) {
        		workAlarmDTO.setAlarmListId(deleteRows[i]);
        		
        		list.add((WorkAlarmDTO) BeanUtils.cloneBean(workAlarmDTO));
        	}
        	result = workAlarmDAO.deleteList(list, user);
        }
        
        return result;
    }

	@Override
	public WorkAlarmDTO findDetail(WorkAlarmDTO workAlarmDTO, User user) throws Exception 
	{
		return (WorkAlarmDTO)CommonUtil.makeDetailFromList(workAlarmDAO.findList(workAlarmDTO, user), workAlarmDTO);
	}
	
	@Override
    public int updateDetail(WorkAlarmDTO workAlarmDTO, User user) throws Exception
    {
        int[] result = null;
        
        List<WorkAlarmDTO> list = new ArrayList<WorkAlarmDTO>();
        list.add(workAlarmDTO);
        
        result = workAlarmDAO.updateDetail(list, user);
        
        return result[0];
    }

	public int[] insertDetail(List<WorkAlarmDTO> list, User user) throws Exception
    {
        return workAlarmDAO.insertDetail(list, user);
    }
	
	@Override
	public WorkAlarmDTO insertWoReqWithAlarm(WorkAlarmDTO workAlarmDTO, User user) {
		// DTO Validation
		workAlarmDTO = chkValidDTO(workAlarmDTO);
		
		if(!"N".equals(workAlarmDTO.getIsAlarmSuccess())) {
			try {
				// 1. WorkAlarmDTO 값을 확인하여 taalarmlist를 INSERT
		        workAlarmDTO.setAlarmListId(workAlarmDAO.getNextSequence("SQAALARMLIST_ID"));
		        
		        // 설비ID가 없을 시 Alarm 설비코드(tag_no)로 설비 조회 후 입력
		     	if(CommonUtil.isNullCheck(workAlarmDTO.getEquipId())){
		     		workAlarmDTO.setEquipId(this.findEquipId(workAlarmDTO, user));
		     	}
		        
		     	List<WorkAlarmDTO> list = new ArrayList<WorkAlarmDTO>();
		        list.add(workAlarmDTO);
		        this.insertDetail(list, user);
		        
		        int rtn = 0;
		        
		        //2. alarmStatus 상태에 따라 수리요청접수 등록
		        if(!"".equals(workAlarmDTO.getAlarmStatus())) {
		        	// 작업요청서를 작성중으로 생성
		        	// INSERT TAALARMREQ, TAWOREQ 
		        	// TAWOREQ.WOREQ_STATUS = 'WRT'
		        	if("WRT".equals(workAlarmDTO.getAlarmStatus())) {
		        		workAlarmDTO.setWoReqId(insertWoReq(workAlarmDTO, user));
			        	
		        		rtn = insertAlarmReq(workAlarmDTO, user);
			        } 
		        	// 작업요청서를 요청상태로 생성
			        // INSERT TAALARMREQ, TAWOREQ 
			        // TAWOREQ.WOREQ_STATUS = 'REQ'
		        	else if("REQ".equals(workAlarmDTO.getAlarmStatus())){
		        		workAlarmDTO.setWoReqId(insertWoReq(workAlarmDTO, user));
			        	
			        	rtn = insertAlarmReq(workAlarmDTO, user);
			        }	
		        }
		        // 성공여부 
		        if(rtn > 0) {
		        	workAlarmDTO.setIsAlarmSuccess("Y");
		        	workAlarmDTO.setAlarmFailMsg("등록 성공");
		        } else {
		        	workAlarmDTO.setIsAlarmSuccess("N");
			        workAlarmDTO.setAlarmFailMsg("등록 실패");
		        }
			} catch(Exception e) {
				workAlarmDTO.setIsAlarmSuccess("N");
		        workAlarmDTO.setAlarmFailMsg("오류 : "+ e.getCause());
		        e.printStackTrace();
			}
		}
				
		return workAlarmDTO;
	}

	public int insertDetail(WorkAlarmDTO workAlarmDTO, User user) throws Exception
    {
       	if(CommonUtil.isNullCheck(workAlarmDTO.getAlarmListId()))
       		workAlarmDTO.setAlarmListId(workAlarmDAO.getNextSequence("SQAALARMLIST_ID"));
        
        // 설비ID가 없을 시 Alarm 설비코드(tag_no)로 설비 조회 후 입력
     	if(CommonUtil.isNullCheck(workAlarmDTO.getEquipId())){
     		workAlarmDTO.setEquipId(this.findEquipId(workAlarmDTO, user));
     	}
        
        List<WorkAlarmDTO> list = new ArrayList<WorkAlarmDTO>();
        list.add((WorkAlarmDTO) BeanUtils.cloneBean(workAlarmDTO));
        
        int[] rtn =  this.insertDetail(list, user);
        
        return rtn[0];
    }
	
	@Override
	public String insertWoReq(WorkAlarmDTO workAlarmDTO, User user) throws Exception {
		MaWoReqDetailDAO maWoReqDetailDAO = (MaWoReqDetailDAO)CommonUtil.getBean("maWoReqDetailDAO", user);
        MaWoReqDetailService maWoReqDetailService = (MaWoReqDetailService)CommonUtil.getBean("maWoReqDetailService", user);
        MaWoReqDetailDTO maWoReqDetailDTO = new MaWoReqDetailDTO();
        
        maWoReqDetailDTO.setWoReqId(maWoReqDetailDAO.getNextSequence("SQAWOREQ_ID"));
        maWoReqDetailDTO.setWoReqNo(maWoReqDetailDTO.getWoReqId());
        if(!CommonUtil.isNullCheck(workAlarmDTO.getAlarmStartTime()))
        	maWoReqDetailDTO.setReqDate(CommonUtil.convertDate(workAlarmDTO.getAlarmStartTime().substring(0, 8)));
        
        if(!CommonUtil.isNullCheck(workAlarmDTO.getAlarmEndTime()))
        	maWoReqDetailDTO.setReqComDate(CommonUtil.convertDate(workAlarmDTO.getAlarmEndTime().substring(0, 8)));
        
        StringBuffer sb = new StringBuffer();
        List<String> reqDesc = new ArrayList<String>();
        
        reqDesc.add(workAlarmDTO.getAlarmMcDesc());
        reqDesc.add(workAlarmDTO.getAlarmPoint());
        reqDesc.add(workAlarmDTO.getAlarmType());
        
        for (int i = 0; i < reqDesc.size(); i++) {
			if(i==0) sb.append(reqDesc.get(0));
			else{
				if("".equals(reqDesc.get(i))) sb.append(reqDesc.get(i));
				else{
					sb.append(" ");
					sb.append(reqDesc.get(i));
				}
			}
		}
        
        maWoReqDetailDTO.setReqDesc(sb.toString());
        maWoReqDetailDTO.setReqEquipId(workAlarmDTO.getEquipId());
        maWoReqDetailDTO.setReqEquipDesc(workAlarmDTO.getEquipDesc());
        maWoReqDetailDTO.setWoReqStatusId(workAlarmDTO.getAlarmStatus());
        maWoReqDetailDTO.setReqEmpId(workAlarmDTO.getReqEmpId());
        maWoReqDetailDTO.setReqPhone(workAlarmDTO.getReqEmpPhone());
        maWoReqDetailDTO.setReqEmail(workAlarmDTO.getReqEmpMail());
        maWoReqDetailDTO.setPlantId(workAlarmDTO.getPlant());
        maWoReqDetailDTO.setReqRequest(workAlarmDTO.getAlarmName());
        
        maWoReqDetailService.insertDetail(maWoReqDetailDTO, user);
       return maWoReqDetailDTO.getWoReqId();
	}
	
	@Override
	public int insertAlarmReq(WorkAlarmDTO workAlarmDTO, User user) throws Exception {
		WorkAlarmReqService workAlarmReqService = (WorkAlarmReqService)CommonUtil.getBean("workAlarmReqService", user);
        WorkAlarmReqDTO workAlarmReqDTO = new WorkAlarmReqDTO();
        
        workAlarmReqDTO.setAlarmListId(workAlarmDTO.getAlarmListId());
        workAlarmReqDTO.setWoReqId(workAlarmDTO.getWoReqId());
        
        return workAlarmReqService.insertDetail(workAlarmReqDTO, user);
	}

	@Override
	public String findEquipId(WorkAlarmDTO workAlarmDTO, User user) throws Exception {
		MaEqMstrListService maEqMstrListService = (MaEqMstrListService)CommonUtil.getBean("maEqMstrListService", user);
		MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
		
		maEqMstrCommonDTO.setFilterTagNo(workAlarmDTO.getAlarmMcCode());
		
		maEqMstrCommonDTO = (MaEqMstrCommonDTO) CommonUtil.makeDetailFromList(maEqMstrListService.findEqMstrList(maEqMstrCommonDTO, user), maEqMstrCommonDTO);
		return maEqMstrCommonDTO.getEquipId();
	}
	
	public WorkAlarmDTO chkValidDTO(WorkAlarmDTO workAlarmDTO) {
		// 날짜 및 날짜 + 시간의 데이터에 숫자의 데이터만 존재하는지 확인
		if(!Pattern.matches("^[0-9]*$", workAlarmDTO.getAlarmStartTime()) || !Pattern.matches("^[0-9]*$", workAlarmDTO.getAlarmEndTime())){
			workAlarmDTO.setIsAlarmSuccess("N");
			workAlarmDTO.setAlarmFailMsg("날짜 및 시간은 숫자만 입력");
		}
		
		// 날짜 길이 확인
		if((!CommonUtil.isNullCheck(workAlarmDTO.getAlarmStartTime()) && workAlarmDTO.getAlarmStartTime().length() < 8) || (!CommonUtil.isNullCheck(workAlarmDTO.getAlarmEndTime()) && workAlarmDTO.getAlarmEndTime().length() < 8)){
			workAlarmDTO.setIsAlarmSuccess("N");
			workAlarmDTO.setAlarmFailMsg("날짜를 8자 이상 입력");
		}
			
		// 설비ID에 숫자의 데이터만 존재하는지 확인
		if(!Pattern.matches("^[0-9]*$", workAlarmDTO.getEquipId())) {
			workAlarmDTO.setIsAlarmSuccess("N");
			workAlarmDTO.setAlarmFailMsg("ID 값은 숫자만 입력");
		}
		
		return workAlarmDTO;
	}
}
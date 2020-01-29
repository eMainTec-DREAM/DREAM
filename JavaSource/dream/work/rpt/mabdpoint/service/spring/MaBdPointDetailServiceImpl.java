package dream.work.rpt.mabdpoint.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultPointListDTO;
import dream.work.list.dto.WorkListPointListDTO;
import dream.work.list.dto.WorkListPtrlResultCommonDTO;
import dream.work.list.service.MaWoResultPointListService;
import dream.work.list.service.WorkListPointListService;
import dream.work.pm.list.dto.WorkPmiCInsCommonDTO;
import dream.work.pm.list.dto.WorkPmiDInsCommonDTO;
import dream.work.pm.list.service.WorkPmiCInsPointListService;
import dream.work.pm.list.service.WorkPmiDInsPointListService;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;
import dream.work.pmi.list.dto.WorkPmiPointListDTO;
import dream.work.pmi.list.service.WorkPmiPointListService;
import dream.work.rpt.mabdpoint.dao.MaBdPointDetailDAO;
import dream.work.rpt.mabdpoint.dto.MaBdPointCommonDTO;
import dream.work.rpt.mabdpoint.dto.MaBdPointDetailDTO;
import dream.work.rpt.mabdpoint.service.MaBdPointDetailService;

/**
 * 이상점검조치 - 상세 serviceimpl 
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maBdPointDetailServiceTarget"
 * @spring.txbn id="maBdPointDetailService"
 * @spring.property name="maBdPointDetailDAO" ref="maBdPointDetailDAO"
 */
public class MaBdPointDetailServiceImpl implements MaBdPointDetailService
{
    private MaBdPointDetailDAO maBdPointDetailDAO = null;
    
    public MaBdPointDetailDAO getMaBdPointDetailDAO() 
    {
		return maBdPointDetailDAO;
	}

	public void setMaBdPointDetailDAO(MaBdPointDetailDAO maBdPointDetailDAO) 
	{
		this.maBdPointDetailDAO = maBdPointDetailDAO;
	}

	public MaBdPointDetailDTO findDetail(MaBdPointCommonDTO maBdPointCommonDTO, User loginUser)throws Exception
    {
		String pmi_type = maBdPointDetailDAO.selectPmiType(maBdPointCommonDTO, loginUser);
		MaBdPointDetailDTO maBdPointDetailDTO = null;
		if("INS".equals(pmi_type)){
			maBdPointDetailDTO = maBdPointDetailDAO.findInsDetail(maBdPointCommonDTO, loginUser);
		}else if("PINS".equals(pmi_type)){
			maBdPointDetailDTO = maBdPointDetailDAO.findPInsDetail(maBdPointCommonDTO, loginUser);
		}else if("RINS".equals(pmi_type)){
			maBdPointDetailDTO = maBdPointDetailDAO.findRInsDetail(maBdPointCommonDTO, loginUser);
		}else if("DINS".equals(pmi_type)){
			maBdPointDetailDTO = maBdPointDetailDAO.findDInsDetail(maBdPointCommonDTO, loginUser);
		}else if("CINS".equals(pmi_type)){
			maBdPointDetailDTO = maBdPointDetailDAO.findCInsDetail(maBdPointCommonDTO, loginUser);
		}
		
		return maBdPointDetailDTO;
    }
	
	public int checkNgPoint(String pmiType, String listKeyId, String status, String actualDate, User user) throws Exception
	{   
		int resultCnt = 0;
		String pointKeyId = "";
		String remark ="";
		String rsltValue = "";
		String pmPointId = "";
		List pmiPointList = null;
		
		if("C".equals(status))
		{	// 확정시
			//pmiType에 따른 점검항목 데이터 조회
			switch(pmiType)
			{
				case "INS" : 
						MaWoResultPointListService maWoResultPointListService = (MaWoResultPointListService) CommonUtil.getBean("maWoResultPointListService", user);
						MaWoResultMstrCommonDTO maWoResultMstrCommonDTO= new MaWoResultMstrCommonDTO();
						MaWoResultPointListDTO maWoResultPointListDTO= new MaWoResultPointListDTO();
						
						maWoResultMstrCommonDTO.setWkOrId(listKeyId);
						pmiPointList = maWoResultPointListService.findPointList(maWoResultMstrCommonDTO, maWoResultPointListDTO, user);	
				
						//점검항목중 pm_point_rslt_status가 이상(BD)인 점검항목 색출
						for(Object obj : pmiPointList)
						{
							Map pointMap = (Map) obj;
							
							pointKeyId = pointMap.get("woPointId").toString();
							pmPointId = pointMap.get("pmPointId").toString();
							
							if(null != pointMap.get("resultRemark"))
								remark = pointMap.get("resultRemark").toString();
							
							if(null != pointMap.get("resultValue"))
								rsltValue = pointMap.get("resultValue").toString();
							
							if("BD".equals(pointMap.get("pmPointRsltStatus").toString()))
							{	//점검결과가 이상인 경우
								
								//TAWONGPOINT 에 이미 생성된 데이터가 있는지 확인
								if("0".equals(maBdPointDetailDAO.getWongPointCount(pmiType, listKeyId, pointKeyId, user)))
								{	//없는경우 TAWONGPOINT 에 데이터 입력
									maBdPointDetailDAO.insertWongDetail(pmiType, listKeyId, pmPointId, pointKeyId, actualDate, rsltValue, remark, user);
								}
								else
								{	//있는경우 기존의 데이터를 수정
									maBdPointDetailDAO.updateWongDetail(pmiType, listKeyId, pointKeyId, actualDate, rsltValue, remark, user);
								} 
							} // end of if
							
							pointKeyId = "";
							pmPointId = "";
							remark = "";
							rsltValue = "";
							
						} // end of for
						
						break;
						
				case "PINS" : 
					WorkListPointListService workListPointListService = (WorkListPointListService) CommonUtil.getBean("workListPointListService", user);
						WorkListPointListDTO workListPointListDTO = new WorkListPointListDTO();
						
						workListPointListDTO.setFilterPmPtrlRsltListId(listKeyId);
						pmiPointList = workListPointListService.findList(new WorkListPtrlResultCommonDTO(), workListPointListDTO, user);	
				
						//점검항목중 pm_point_rslt_status가 이상(BD)인 점검항목 색출
						for(Object obj : pmiPointList)
						{
							Map pointMap = (Map) obj;
							
							pointKeyId = pointMap.get("pmPtrlRsltPointId").toString();
							pmPointId = pointMap.get("pmPointId").toString();
							rsltValue = "";
							
							if(null != pointMap.get("remark"))
								remark = pointMap.get("remark").toString();
							
							if("BD".equals(pointMap.get("pmPointRsltStatusId").toString()))
							{	//점검결과가 이상인 경우
								
								//TAWONGPOINT 에 이미 생성된 데이터가 있는지 확인
								if("0".equals(maBdPointDetailDAO.getWongPointCount(pmiType, listKeyId, pointKeyId, user)))
								{	//없는경우 TAWONGPOINT 에 데이터 입력
									maBdPointDetailDAO.insertWongDetail(pmiType, listKeyId, pmPointId, pointKeyId, actualDate, rsltValue, remark, user);
								}
								else
								{	//있는경우 기존의 데이터를 수정
									maBdPointDetailDAO.updateWongDetail(pmiType, listKeyId, pointKeyId, actualDate, rsltValue, remark, user);
								} 
							} // end of if
							
							pointKeyId = "";
							pmPointId = "";
							remark = "";
							rsltValue = "";
							
						} // end of for
						break;
					
				case "RINS" : 
						WorkPmiPointListService workPmiPointListService = (WorkPmiPointListService) CommonUtil.getBean("workPmiPointListService", user);
						WorkPmiCommonDTO workPmiCommonDTO = new WorkPmiCommonDTO();
						WorkPmiPointListDTO workPmiPointListDTO = new WorkPmiPointListDTO();
						
						workPmiCommonDTO.setPmschedStatusId("C");
						workPmiCommonDTO.setPminslistId(listKeyId);
						
						System.out.println("workPmiCommonDTO.getpminslistid ::: " + workPmiCommonDTO.getPminslistId());
						System.out.println("workPmiCommonDTO.getPmschedStatusId ::: " + workPmiCommonDTO.getPmschedStatusId());
						
						pmiPointList = workPmiPointListService.findPointList(workPmiCommonDTO, workPmiPointListDTO, user);	
				
						//점검항목중 pm_point_rslt_status가 이상(BD)인 점검항목 색출
						for(Object obj : pmiPointList)
						{
							Map pointMap = (Map) obj;
							
							pointKeyId = pointMap.get("pmInsPointId").toString();
							pmPointId = pointMap.get("pmPointId").toString();
							
							if(null != pointMap.get("resultRemark"))
								remark = pointMap.get("resultRemark").toString();
							
							if(null != pointMap.get("resultValue"))
								rsltValue = pointMap.get("resultValue").toString();
							
							if("BD".equals(pointMap.get("pmPointRsltStatus").toString()))
							{	//점검결과가 이상인 경우
								
								//TAWONGPOINT 에 이미 생성된 데이터가 있는지 확인
								if("0".equals(maBdPointDetailDAO.getWongPointCount(pmiType, listKeyId, pointKeyId, user)))
								{	//없는경우 TAWONGPOINT 에 데이터 입력
									maBdPointDetailDAO.insertWongDetail(pmiType, listKeyId, pmPointId, pointKeyId, actualDate, rsltValue, remark, user);
								}
								else
								{	//있는경우 기존의 데이터를 수정
									maBdPointDetailDAO.updateWongDetail(pmiType, listKeyId, pointKeyId, actualDate, rsltValue, remark, user);
								} 
							} // end of if
							
							pointKeyId = "";
							pmPointId = "";
							remark = "";
							rsltValue = "";
							
						} // end of for
						
						break;
						
				case "DINS" : 
						WorkPmiDInsPointListService workPmiDInsPointListService = (WorkPmiDInsPointListService) CommonUtil.getBean("workPmiDInsPointListService", user);
						WorkPmiDInsCommonDTO workPmiDInsCommonDTO= new WorkPmiDInsCommonDTO();
						
						workPmiDInsCommonDTO.setPmInsDListId(listKeyId);
						pmiPointList = workPmiDInsPointListService.findList(workPmiDInsCommonDTO, user);	
				
						//점검항목중 pm_point_rslt_status가 이상(BD)인 점검항목 색출
						for(Object obj : pmiPointList)
						{
							Map pointMap = (Map) obj;
							
							pointKeyId = pointMap.get("pmInsDPointId").toString();
							pmPointId = pointMap.get("pmPointId").toString();
							
							if(null != pointMap.get("insDetail"))
								remark = pointMap.get("insDetail").toString();
							
							if(null != pointMap.get("resultValue"))
								rsltValue = pointMap.get("resultValue").toString();
							
							if("BD".equals(pointMap.get("pmPointRsltStatusId").toString()))
							{	//점검결과가 이상인 경우
								
								//TAWONGPOINT 에 이미 생성된 데이터가 있는지 확인
								if("0".equals(maBdPointDetailDAO.getWongPointCount(pmiType, listKeyId, pointKeyId, user)))
								{	//없는경우 TAWONGPOINT 에 데이터 입력
									maBdPointDetailDAO.insertWongDetail(pmiType, listKeyId, pmPointId, pointKeyId, actualDate, rsltValue, remark, user);
								}
								else
								{	//있는경우 기존의 데이터를 수정
									maBdPointDetailDAO.updateWongDetail(pmiType, listKeyId, pointKeyId, actualDate, rsltValue, remark, user);
								} 
							} // end of if
							
							pointKeyId = "";
							pmPointId = "";
							remark = "";
							rsltValue = "";
							
						} // end of for
						
						break;
						
				case "CINS" : 
					WorkPmiCInsPointListService workPmiCInsPointListService = (WorkPmiCInsPointListService) CommonUtil.getBean("workPmiCInsPointListService", user);
					WorkPmiCInsCommonDTO workPmiCInsCommonDTO= new WorkPmiCInsCommonDTO();
					
					workPmiCInsCommonDTO.setPmInsDListId(listKeyId);
					pmiPointList = workPmiCInsPointListService.findList(workPmiCInsCommonDTO, user);	
					
					//점검항목중 pm_point_rslt_status가 이상(BD)인 점검항목 색출
					for(Object obj : pmiPointList)
					{
						Map pointMap = (Map) obj;
						
						pointKeyId = pointMap.get("pmInsDPointId").toString();
						pmPointId = pointMap.get("pmPointId").toString();
						
						if(null != pointMap.get("insDetail"))
							remark = pointMap.get("insDetail").toString();
						
						if(null != pointMap.get("resultValue"))
							rsltValue = pointMap.get("resultValue").toString();
						
						if("BD".equals(pointMap.get("pmPointRsltStatusId").toString()))
						{	//점검결과가 이상인 경우
							
							//TAWONGPOINT 에 이미 생성된 데이터가 있는지 확인
							if("0".equals(maBdPointDetailDAO.getWongPointCount(pmiType, listKeyId, pointKeyId, user)))
							{	//없는경우 TAWONGPOINT 에 데이터 입력
								maBdPointDetailDAO.insertWongDetail(pmiType, listKeyId, pmPointId, pointKeyId, actualDate, rsltValue, remark, user);
							}
							else
							{	//있는경우 기존의 데이터를 수정
								maBdPointDetailDAO.updateWongDetail(pmiType, listKeyId, pointKeyId, actualDate, rsltValue, remark, user);
							} 
						} // end of if
						
						pointKeyId = "";
						pmPointId = "";
						remark = "";
						rsltValue = "";
						
					} // end of for
					
					break;
					
			} //end of switch
		} // end of if
		
		else
		{	// 확정취소시
			// 즉시조치,작업요청,작업계획,작업결과 데이터가 없는경우 삭제
			maBdPointDetailDAO.deleteWongPoint(pmiType, listKeyId, user);
		}
		
		return resultCnt;
	}
	
	public int updateDetail(MaBdPointDetailDTO maBdPointDetailDTO) throws Exception
    {   
	    int resultCnt = 0;
//	    resultCnt += maBdPointDetailDAO.updateDetail(maBdPointDetailDTO);
	    resultCnt += maBdPointDetailDAO.updateNgDetail(maBdPointDetailDTO);
        return resultCnt;
    }
	
	public String findId(MaBdPointDetailDTO maBdPointDetailDTO, User loginUser) throws Exception
	{
		return maBdPointDetailDAO.findId(maBdPointDetailDTO,loginUser);
	}
	
	public String findStatus(MaBdPointCommonDTO maBdPointCommonDTO, User user) throws Exception
	{
		String pmi_type = maBdPointDetailDAO.selectPmiType(maBdPointCommonDTO, user);
		
		MaBdPointDetailDTO maBdPointDetailDTO = null;
		
		if("INS".equals(pmi_type)){
			maBdPointDetailDTO = maBdPointDetailDAO.findInsDetail(maBdPointCommonDTO, user);
		}else if("PINS".equals(pmi_type)){
			maBdPointDetailDTO = maBdPointDetailDAO.findPInsDetail(maBdPointCommonDTO, user);
		}else if("RINS".equals(pmi_type)){
			maBdPointDetailDTO = maBdPointDetailDAO.findRInsDetail(maBdPointCommonDTO, user);
		}else if("DINS".equals(pmi_type)){
			maBdPointDetailDTO = maBdPointDetailDAO.findDInsDetail(maBdPointCommonDTO, user);
		}else if("CINS".equals(pmi_type)){
			maBdPointDetailDTO = maBdPointDetailDAO.findDInsDetail(maBdPointCommonDTO, user);
		}
		
		return maBdPointDetailDTO.getPmPointRepStatus();
	}
}

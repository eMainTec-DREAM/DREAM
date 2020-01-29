package dream.work.pm.list.service.spring;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.fins.gt.util.StringUtils;

import common.bean.User;
import common.util.CommonUtil;
import common.util.StringUtil;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultPointListDTO;
import dream.work.list.dto.WorkListPointListDTO;
import dream.work.list.dto.WorkListPtrlResultCommonDTO;
import dream.work.list.service.MaWoResultPointListService;
import dream.work.list.service.WorkListPointListService;
import dream.work.pm.list.dao.MaPmMstrPointListDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrPointDetailDTO;
import dream.work.pm.list.dto.WorkPmiDInsCommonDTO;
import dream.work.pm.list.service.MaPmMstrPointDetailService;
import dream.work.pm.list.service.MaPmMstrPointListService;
import dream.work.pm.list.service.WorkPmiDInsPointListService;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;
import dream.work.pmi.list.dto.WorkPmiPointListDTO;
import dream.work.pmi.list.service.WorkPmiPointListService;

/**
 * 검사항목 목록
 * @author jung7126
 * @version $Id: MaPmMstrPointListServiceImpl.java,v 1.0 2015/12/02 09:12:51 jung7126 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maPmMstrPointListServiceTarget"
 * @spring.txbn id="maPmMstrPointListService"
 * @spring.property name="maPmMstrPointListDAO" ref="maPmMstrPointListDAO"
 * @spring.property name="maPmMstrPointDetailService" ref="maPmMstrPointDetailService"
 */
public class MaPmMstrPointListServiceImpl implements MaPmMstrPointListService
{
    private MaPmMstrPointListDAO maPmMstrPointListDAO = null;
    private MaPmMstrPointDetailService maPmMstrPointDetailService = null;


	public MaPmMstrPointListDAO getMaPmMstrPointListDAO() {
		return maPmMstrPointListDAO;
	}

	public void setMaPmMstrPointListDAO(MaPmMstrPointListDAO maPmMstrPointListDAO) {
		this.maPmMstrPointListDAO = maPmMstrPointListDAO;
	}

	public MaPmMstrPointDetailService getMaPmMstrPointDetailService() {
		return maPmMstrPointDetailService;
	}

	public void setMaPmMstrPointDetailService(MaPmMstrPointDetailService maPmMstrPointDetailService) {
		this.maPmMstrPointDetailService = maPmMstrPointDetailService;
	}

	public List findPointList(MaPmMstrCommonDTO maPmMstrCommonDTO, User loginUser)
    {      
        return maPmMstrPointListDAO.findPointList(maPmMstrCommonDTO, loginUser);
    }
	
	public int deletePointList(String[] deleteRows, User loginUser) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null) && deleteRows.length>0) {
            String pmPointId = StringUtils.join(deleteRows, "+");
            
            //DELETE TAPMINSPOINT
            WorkPmiPointListService workPmiPointListService = (WorkPmiPointListService) CommonUtil.getBean("workPmiPointListService", loginUser);
            WorkPmiPointListDTO workPmiPointListDTO = new WorkPmiPointListDTO();
            workPmiPointListDTO.setPmPointId(pmPointId);
            workPmiPointListDTO.setPmSchedStatus("-C");
            String[] pmInsPointIds = StringUtil.toSingleArray(workPmiPointListService.findPointList(new WorkPmiCommonDTO(), workPmiPointListDTO, loginUser), "PMINSPOINTID");
            workPmiPointListService.deletePointList(pmInsPointIds, loginUser);
            
            //DELETE TAPMINSDPOINT
            WorkPmiDInsPointListService workPmiDInsPointListService = (WorkPmiDInsPointListService) CommonUtil.getBean("workPmiDInsPointListService", loginUser);
            WorkPmiDInsCommonDTO workPmiDInsCommonDTO = new WorkPmiDInsCommonDTO();
            workPmiDInsCommonDTO.setPmPointId(pmPointId);
            workPmiDInsCommonDTO.setPmSchedStatus("-C");
            String[] pmInsDPointIds = StringUtil.toSingleArray(workPmiDInsPointListService.findList(workPmiDInsCommonDTO, loginUser), "PMINSDPOINTID");
            workPmiDInsPointListService.deleteList(pmInsDPointIds, loginUser);
            
            //DELETE TAPMPTRLRSLTPOINT
            WorkListPointListService workListPointListService = (WorkListPointListService) CommonUtil.getBean("workListPointListService", loginUser);
            WorkListPointListDTO workListPointListDTO = new WorkListPointListDTO();
            workListPointListDTO.setPmPointId(pmPointId);
            workListPointListDTO.setPmSchedStatus("-C");
            String[] pmPtrlRsltPointIds = StringUtil.toSingleArray(workListPointListService.findList(new WorkListPtrlResultCommonDTO(), workListPointListDTO, loginUser), "PMPTRLRSLTPOINTID");
            workListPointListService.deleteList(pmPtrlRsltPointIds, loginUser);
            
            //DELETE TAWOPOINT
            MaWoResultPointListService maWoResultPointListService = (MaWoResultPointListService) CommonUtil.getBean("maWoResultPointListService", loginUser);
            MaWoResultPointListDTO maWoResultPointListDTO = new MaWoResultPointListDTO();
            maWoResultPointListDTO.setPmPointId(pmPointId);
            maWoResultPointListDTO.setPmSchedStatus("-C");
            String[] woPointIds = StringUtil.toSingleArray(maWoResultPointListService.findPointList(new MaWoResultMstrCommonDTO(), maWoResultPointListDTO, loginUser), "WOPOINTID");
            maWoResultPointListService.deletePointList(woPointIds, loginUser);
            
            //DELETE TAPMPOINT
            List list = new ArrayList();
            MaPmMstrPointDetailDTO maPmMstrPointDetailDTO = new MaPmMstrPointDetailDTO();
            for(String id : deleteRows)
            {
                maPmMstrPointDetailDTO.setPmPointId(id);
                list.add(BeanUtils.cloneBean(maPmMstrPointDetailDTO));
            }
            result = maPmMstrPointListDAO.updateDeleteTag(list, loginUser).length;
        }
        
        return result;
    }

	@Override
	public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception {
		return maPmMstrPointListDAO.findTotalCount(maPmMstrCommonDTO, user);
	}

	@Override
	public int insertPointList(MaPmMstrPointDetailDTO maPmMstrPointDetailDTO, MaPmMstrCommonDTO maPmMstrMstrCommonDTO, User user) throws Exception {
		
    	int result = 0;

    	return result;
	}

	@Override
	public int insertLovDetail(MaPmMstrPointDetailDTO maPmMstrPointDetailDTO, MaPmMstrCommonDTO maPmMstrMstrCommonDTO,
			User user) throws Exception {
    	int result = 0;
    	
    	String[] multiKey = maPmMstrPointDetailDTO.getMultiKey().split("\\+");
    	
        for(int i=0; multiKey.length > i; i++)
        {	
        	maPmMstrPointDetailDTO.setPmPointId(maPmMstrPointListDAO.getNextSequence("SQAMTPOINT_ID"));//list id
        	maPmMstrPointDetailDTO.setEqCtgPmPointId(multiKey[i]);//lov id
   
            result = result + maPmMstrPointDetailService.insertLovDetail(maPmMstrPointDetailDTO, maPmMstrMstrCommonDTO, user);
            
        }
    	return result;
	}
}


package dream.work.pm.list.service.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import com.fins.gt.util.StringUtils;

import common.bean.MwareConfig;
import common.bean.User;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.StringUtil;
import dream.comm.revision.dto.CommRevCommonDTO;
import dream.comm.revision.service.CommRevService;
import dream.work.pm.list.dao.MaPmMstrDetailDAO;
import dream.work.pm.list.dao.MaPmMstrListDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrDetailDTO;
import dream.work.pm.list.service.MaPmMstrDetailService;
import dream.work.pm.list.service.MaPmMstrListService;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;
import dream.work.pmi.list.service.WorkPmiListService;
import dream.work.pm.list.service.MaPmMstrPartListService;
import dream.work.pm.list.service.MaPmMstrPointListService;
import dream.work.pm.list.service.WorkPmListEquipListService;

/**
 * �����۾����� - ��� serviceimpl
 * @author jung7126
 * @version $Id: MaPmMstrListServiceImpl.java,v 1.0 2015/12/02 09:12:51 jung7126 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maPmMstrListServiceTarget"
 * @spring.txbn id="maPmMstrListService"
 * @spring.property name="maPmMstrListDAO" ref="maPmMstrListDAO"
 * @spring.property name="commRevService" ref="commRevService"
 * @spring.property name="maPmMstrDetailDAO" ref="maPmMstrDetailDAO"
 */
public class MaPmMstrListServiceImpl implements MaPmMstrListService
{
    private MaPmMstrListDAO maPmMstrListDAO = null;
    private CommRevService commRevService = null;
    private MaPmMstrDetailDAO maPmMstrDetailDAO = null;
    
	public MaPmMstrDetailDAO getMaPmMstrDetailDAO() {
		return maPmMstrDetailDAO;
	}
	public void setMaPmMstrDetailDAO(MaPmMstrDetailDAO maPmMstrDetailDAO) {
		this.maPmMstrDetailDAO = maPmMstrDetailDAO;
	}
	public CommRevService getCommRevService() {
		return commRevService;
	}
	public void setCommRevService(CommRevService commRevService) {
		this.commRevService = commRevService;
	}
	public MaPmMstrListDAO getMaPmMstrListDAO() {
		return maPmMstrListDAO;
	}
	public void setMaPmMstrListDAO(MaPmMstrListDAO maPmMstrListDAO) {
		this.maPmMstrListDAO = maPmMstrListDAO;
	}

	public List findList(MaPmMstrCommonDTO maPmMstrCommonDTO, User user)
    {      
        return maPmMstrListDAO.findList(maPmMstrCommonDTO,user);
    }
	public List findWorkList(MaPmMstrCommonDTO maPmMstrCommonDTO, User user)
    {      
        return maPmMstrListDAO.findWorkList(maPmMstrCommonDTO,user);
    }
	public List findInsList(MaPmMstrCommonDTO maPmMstrCommonDTO, User user)
    {      
        return maPmMstrListDAO.findInsList(maPmMstrCommonDTO,user);
    }
	public List findTrList(MaPmMstrCommonDTO maPmMstrCommonDTO, User user)
    {      
        return maPmMstrListDAO.findTrList(maPmMstrCommonDTO,user);
    }
	public List findCalList(MaPmMstrCommonDTO maPmMstrCommonDTO, User user)
    {      
        return maPmMstrListDAO.findCalList(maPmMstrCommonDTO,user);
    }
	
	public int deleteList(String[] deleteRows, User user) throws Exception
	{
		int result = 0;
        
	    //�簳�� ������� �ʴ� �����۾� ���� ����
        if("N".equals(MwareConfig.getIsUsePmRevision()))
        {
            // �����۾� ����
            this.deletePmList(deleteRows, user);
        }
        else
        {
            if(!deleteRows.equals(null)){
                List deletePmList = new ArrayList();
                for(String id : deleteRows)
                {
                    // �ֽŹ������� Ȯ���� ���� �����ֱ� ��ȸ
            		MaPmMstrDetailService maPmMstrDetailService = (MaPmMstrDetailService)CommonUtil.getBean("maPmMstrDetailService");
                    MaPmMstrCommonDTO maPmMstrCommonDTO = new MaPmMstrCommonDTO();
                    maPmMstrCommonDTO.setPmId(id);
                    MaPmMstrDetailDTO maPmMstrDetailDTO = maPmMstrDetailService.findDetail(maPmMstrCommonDTO, user);
                    
                    // ������ pm����
                    String isLastVersion = maPmMstrDetailDTO.getIsLastVersion();
                    String pmId = maPmMstrDetailDTO.getPmId();
                            
                    // �ش� pm ��� ���� ��ȸ(List)
                    maPmMstrCommonDTO.setPmId("");
                    maPmMstrCommonDTO.setPmNo(maPmMstrDetailDTO.getPmNo());
                    List pmList = maPmMstrListDAO.findList(maPmMstrCommonDTO, user);
                            
                    // �ֽſ��� Y�ΰ��
                    // pm ������ �ش� pm �� �ƴ϶� �ֽŹ������ΰ� "N"�� ��� History pm�� ���� Flag �߰�
                    if("Y".equals(isLastVersion))
                    {
                        if(pmList.size() != 0)
                        {
                            for (int j = 0; j < pmList.size(); j++)
                            {
                                Map mapDto = (Map)pmList.get(j);
                                MaPmMstrDetailDTO pmMstrDetailDTO = (MaPmMstrDetailDTO) CommonUtil.makeDTO(mapDto, MaPmMstrDetailDTO.class);
                                
                                // �ֽſ��� Y�� pm ���� Flag �߰� �� �ش� pmNo�̸鼭 �ֽŹ������ΰ� "N"�� ��� History pmNo�� ���� Flag �߰�
                                deletePmList.add(pmMstrDetailDTO.getPmId());
                            }
                        }
                    }
                            
                    // �ֽſ��� N�� ��� pm ������ ���θ� ���� Flag �߰� 
                    // (�ش� pm�� �׸� �� ������ �̸鼭 �ֽŹ������� "Y"�� pmNo�� ���¸� "�簳���Ϸ�"�� ���� / �������� �׸��� ������ �ֽŹ������� "Y"�� pmNo ���¸� �׳� �д� )
                    else if("N".equals(isLastVersion))
                    {
                        // �ֽſ��� N�� ������ �����۾� ���� Flag �߰�
                        deletePmList.add(pmId);
                        
                    	// �ش� pmNo �� �ֽſ��� "N"�̸鼭 �������� �׸��� �����Ѵٸ� �۾�����(���� �۾��� �������� ����)
    					boolean isFlag = false;
                        if(pmList.size() != 0)
                        {
                        	for (int j = 0; j < pmList.size(); j++) 
    	                	{
    	                		Map mapDto = (Map)pmList.get(j);
    	                		MaPmMstrDetailDTO pmMstrDetailDTO = (MaPmMstrDetailDTO) CommonUtil.makeDTO(mapDto, MaPmMstrDetailDTO.class);
                                
    	                		String revPmId = pmMstrDetailDTO.getPmId();
    	                		String revisionStatusId = pmMstrDetailDTO.getRevisionStatusId();
    	                		String isLastVer = pmMstrDetailDTO.getIsLastVersion();
    	                		
    	                		// ������ �ش� Item�� ������ Item ��
    							// �������̸鼭 �ֽŹ������� "N"�� Item�� �����ϴ��� Ȯ��
    							if(!pmId.equals(revPmId) && "P".equals(revisionStatusId) && "N".equals(isLastVer))
    							{
    								isFlag = true;
    								break;
    							}
    	                	}
                        	
                        	// �ش� pmNo �� �ֽſ��� "N"�̸鼭 �������� �׸��� �������� ������
    						// �ֽŹ������� "Y"�� Item�� ���¸� "�簳���Ϸ�"�� ����
    						if(!isFlag)
    						{
	                            for (int j = 0; j < pmList.size(); j++) 
	                            {
	                                Map mapDto = (Map)pmList.get(j);
	                                MaPmMstrDetailDTO pmMstrDetailDTO = (MaPmMstrDetailDTO) CommonUtil.makeDTO(mapDto, MaPmMstrDetailDTO.class);
	                                
	                                String revPmId = pmMstrDetailDTO.getPmId();
	                                String isLastVer = pmMstrDetailDTO.getIsLastVersion();
	                                
	                                // ������ �ش� pm�� ������ pmNo �� �ֽŹ������� "Y"�� pmNo�� ���¸� "�簳���Ϸ�"�� ����
	                                if(!pmId.equals(revPmId) && "Y".equals(isLastVer))
	                                {
	                                    String revisionhistId = pmMstrDetailDTO.getRevisionhistId();
	                                    String pmNo = pmMstrDetailDTO.getPmNo();
	                                    
	                                    // ������ �Ϸ�ó�� �ش� pm_id
	                                    CommRevCommonDTO commRevCommonDTO = new CommRevCommonDTO();
	                                    
	                                    commRevCommonDTO.setRevisionhistId(revisionhistId);
	                                    commRevCommonDTO.setObjectId(revPmId);
	                                    commRevCommonDTO.setObjectNo(pmNo);
	                                    commRevCommonDTO.setRevisionObjType("PMSTD");
	                                    
	                                    commRevService.completeRegislate(commRevCommonDTO, user);
	                                }
	                            }
    						}
                        }
                    }
                }
                
                this.deletePmList((String[]) deletePmList.toArray(new String[0]), user);
            }
        }
        return result;
    }
	
	public int deletePmList(String[] deleteRows, User user) throws Exception
	{
		int result = 0;
        
        if(!deleteRows.equals(null) && deleteRows.length>0) {
            String pmId = StringUtils.join(deleteRows, "+");
            
            MaPmMstrCommonDTO maPmMstrCommonDTO = new MaPmMstrCommonDTO();
            maPmMstrCommonDTO.setPmId(pmId);
            
            //DELETE TAPMPOINT
            MaPmMstrPointListService maPmMstrPointListService = (MaPmMstrPointListService) CommonUtil.getBean("maPmMstrPointListService", user);
            String[] pmPointIds = StringUtil.toSingleArray(maPmMstrPointListService.findPointList(maPmMstrCommonDTO, user), "PMPOINTID");
            maPmMstrPointListService.deletePointList(pmPointIds, user);
            
            //DELETE TAPMPART
            MaPmMstrPartListService maPmMstrPartListService = (MaPmMstrPartListService) CommonUtil.getBean("maPmMstrPartListService", user);
            String[] pmPartIds = StringUtil.toSingleArray(maPmMstrPartListService.findPartList(maPmMstrCommonDTO, user), "PMPARTID");
            maPmMstrPartListService.deletePartList(pmPartIds, user);
            
            //DELETE TAPMEQUIP
            WorkPmListEquipListService workPmListEquipListService = (WorkPmListEquipListService) CommonUtil.getBean("workPmListEquipListService", user);
            String[] pmEquipIds = StringUtil.toSingleArray(workPmListEquipListService.findEqList(maPmMstrCommonDTO, user), "PMEQUIPID");
            workPmListEquipListService.deleteEqList(pmEquipIds, user);
            
            //DELETE TAPMLST
            List list = new ArrayList();
            MaPmMstrDetailDTO maPmMstrDetailDTO = new MaPmMstrDetailDTO();
            for(String id : deleteRows)
            {
                maPmMstrDetailDTO.setPmId(id);
                list.add(BeanUtils.cloneBean(maPmMstrDetailDTO));
            }
            result = maPmMstrListDAO.updateListDeleteTag(list, user).length;
        }
        
        return result;
	}
	
	public List copyPm(String[] selectRows, User loginUser) throws Exception{
        int result = 0;
        List resultList = new ArrayList();
        Map map =  null;
        CommRevCommonDTO commRevCommonDTO = new CommRevCommonDTO();
        
        if(!selectRows.equals(null)){
            for(String id : selectRows)
            {
            	//���ο� ������ �ޱ�.
        		String newSeq = maPmMstrListDAO.getNextSequence("SQAPM_ID");
        		String revhistId = maPmMstrListDAO.getNextSequence("SQAREVISIONHIST_ID");
        		
        		commRevCommonDTO.setCompNo(loginUser.getCompNo());
        		commRevCommonDTO.setRevisionhistId(revhistId);
        		commRevCommonDTO.setObjectId(newSeq);
        		commRevCommonDTO.setObjectNo(newSeq);
        		commRevCommonDTO.setRevisionObjType("PMSTD");
        		commRevCommonDTO.setDocNo("");
        		commRevCommonDTO.setRevisionType("C");
        		commRevCommonDTO.setRevNo("");
        		commRevCommonDTO.setWrkDate(CommonUtil.convertDate(DateUtil.getDate()));
        		commRevCommonDTO.setWrkEmpId(loginUser.getEmpId());
        		commRevCommonDTO.setRevDesc("");

        		String revisionStatus = "";
        		if("N".equals(MwareConfig.getIsUsePmRevision())){
            		commRevCommonDTO.setRevisionStatusId("C");
            		commRevCommonDTO.setRevisionStepStatusId("CMP");
            		revisionStatus = "C";
        		}else{
            		commRevCommonDTO.setRevisionStatusId("W");
            		commRevCommonDTO.setRevisionStepStatusId("WRT");
            		revisionStatus = "W";
        		}
        		
        		map = new HashMap();
        		map.put("id", newSeq);
        		resultList.add(map);
        		//pm master insert
        		result +=  maPmMstrListDAO.insertCopyDetail(newSeq, id, revisionStatus, revhistId, loginUser, true);
        		//pm schedule insert 
        		result += maPmMstrListDAO.insertCopyPoint(newSeq, id, loginUser);
        		//pm equip insert
        		result += maPmMstrListDAO.insertCopyEquip(newSeq, id, loginUser);
        		//pm schedule insert 
        		result += maPmMstrListDAO.insertCopySched(newSeq, id, loginUser);
        		//revisionhist insert
        		result += commRevService.insertRevHist(commRevCommonDTO, loginUser);
            }
        }
//        maPmMstrListDAO.SP_IF_UPD_TXPARTS(loginUser);
        
        return resultList;
    }
	
	public int insertQrCode(String[] selectRows, String fileName, User loginUser) throws Exception{
		
		//�ϴ� ����ڿ� insert�Ͽ� ������  ��� �����͸� �����Ѵ�.
		maPmMstrListDAO.deleteQrCode(loginUser, fileName);
	    
        int result = 0;
        if(!selectRows.equals(null))
            for(String id : selectRows)
            {
                result = result + maPmMstrListDAO.insertQrCode(id, fileName,loginUser);
            }
        return result;
    }
	public int insertListQrCode(MaPmMstrCommonDTO maPmMstrCommonDTO, String fileName, User loginUser) throws Exception{
		
		//�ϴ� ����ڿ� insert�Ͽ� ������  ��� �����͸� �����Ѵ�.
		maPmMstrListDAO.deleteQrCode(loginUser, fileName);
		return maPmMstrListDAO.insertListQrCode(maPmMstrCommonDTO, fileName,loginUser);
	}
	@Override
	public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception {
		return maPmMstrListDAO.findTotalCount(maPmMstrCommonDTO, user);
	}
	
	@Override
	public String getData(User user,  MaPmMstrCommonDTO maPmMstrCommonDTO) {
		
		return maPmMstrListDAO.getData(user, maPmMstrCommonDTO);
	}
}


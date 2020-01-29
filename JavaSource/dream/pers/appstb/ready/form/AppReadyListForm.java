package dream.pers.appstb.ready.form;

import common.struts.BaseForm;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.invt.list.dto.InvtCommonDTO;
import dream.note.daily.dto.MaWoDailyCommonDTO;
import dream.part.adj.stktake.dto.PartAdjStkTakeCommonDTO;
import dream.part.iss.emg.req.dto.PartIssEmgReqCommonDTO;
import dream.part.pur.buy.dto.MaPtBuyReqHdrCommonDTO;
import dream.pers.appstb.ready.dto.AppReadyCommonDTO;
import dream.req.work.dto.ReqWorkCommonDTO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprCommonDTO;
import dream.work.fmea.list.dto.WorkFmeaCommonDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.planappr.dto.WorkPlanApprCommonDTO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;

/**
 * ���
 * @author  javaworker
 * @version $Id: AppReadyListForm.java,v 1.1 2013/08/30 09:13:19 javaworker Exp $
 * @since   1.0
 *
 * @struts.form name="appReadyListForm"
 */
public class AppReadyListForm extends BaseForm
{    
    /** ���� */
    private AppReadyCommonDTO appReadyCommonDTO = new AppReadyCommonDTO();
    /** �����۾�����Ȯ�� */
    private MaWoDailyCommonDTO maWoDailyCommonDTO = new MaWoDailyCommonDTO();
    /** ����Ȯ�� */
    private InvtCommonDTO invtCommonDTO = new InvtCommonDTO();
    /** �۾���ûȮ�� */
    private ReqWorkCommonDTO reqWorkCommonDTO = new ReqWorkCommonDTO();
    /** ���Ž�ûȮ�� */
    private MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO = new MaPtBuyReqHdrCommonDTO();
    /** ��ǰ�ǻ�Ȯ�� */
    private PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO = new PartAdjStkTakeCommonDTO();
    /** �۾�����Ȯ�� */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
    /** ���念�⼺��Ȯ�� */
    private WorkFmeaCommonDTO workFmeaCommonDTO = new WorkFmeaCommonDTO();
    /** ����ûȮ�� */
    private PartIssEmgReqCommonDTO partIssEmgReqCommonDTO = new PartIssEmgReqCommonDTO();
    /** �۾���ȹȮ�� */
    private WoPlanCommonDTO woPlanCommonDTO = new WoPlanCommonDTO();
    /** �������˽��� */
    private WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO = new WorkCalPmInsApprCommonDTO();
    /** �۾���ȹ���� */
    private WorkPlanApprCommonDTO workPlanApprCommonDTO = new WorkPlanApprCommonDTO();
    /** ���������� ���� */
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
    /** ���˽ǽ� ���� */
    private WorkPmiCommonDTO workPmiCommonDTO = new WorkPmiCommonDTO();
    /** �����ֱ⼳�� ���� */
    private MaPmMstrCommonDTO maPmMstrCommonDTO = new MaPmMstrCommonDTO();
    
    public WorkPmiCommonDTO getWorkPmiCommonDTO() {
		return workPmiCommonDTO;
	}

	public MaPmMstrCommonDTO getMaPmMstrCommonDTO() {
		return maPmMstrCommonDTO;
	}

	public void setMaPmMstrCommonDTO(MaPmMstrCommonDTO maPmMstrCommonDTO) {
		this.maPmMstrCommonDTO = maPmMstrCommonDTO;
	}

	public void setWorkPmiCommonDTO(WorkPmiCommonDTO workPmiCommonDTO) {
		this.workPmiCommonDTO = workPmiCommonDTO;
	}

	public MaEqMstrCommonDTO getMaEqMstrCommonDTO() {
		return maEqMstrCommonDTO;
	}

	public void setMaEqMstrCommonDTO(MaEqMstrCommonDTO maEqMstrCommonDTO) {
		this.maEqMstrCommonDTO = maEqMstrCommonDTO;
	}

	public WorkCalPmInsApprCommonDTO getWorkCalPmInsApprCommonDTO() {
		return workCalPmInsApprCommonDTO;
	}

	public void setWorkCalPmInsApprCommonDTO(WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO) {
		this.workCalPmInsApprCommonDTO = workCalPmInsApprCommonDTO;
	}

	public WorkPlanApprCommonDTO getWorkPlanApprCommonDTO() {
		return workPlanApprCommonDTO;
	}

	public void setWorkPlanApprCommonDTO(WorkPlanApprCommonDTO workPlanApprCommonDTO) {
		this.workPlanApprCommonDTO = workPlanApprCommonDTO;
	}

	public WoPlanCommonDTO getWoPlanCommonDTO()
    {
        return woPlanCommonDTO;
    }

    public void setWoPlanCommonDTO(WoPlanCommonDTO woPlanCommonDTO)
    {
        this.woPlanCommonDTO = woPlanCommonDTO;
    }

    public PartIssEmgReqCommonDTO getPartIssEmgReqCommonDTO()
    {
        return partIssEmgReqCommonDTO;
    }

    public void setPartIssEmgReqCommonDTO(
            PartIssEmgReqCommonDTO partIssEmgReqCommonDTO)
    {
        this.partIssEmgReqCommonDTO = partIssEmgReqCommonDTO;
    }

    public WorkFmeaCommonDTO getWorkFmeaCommonDTO()
    {
        return workFmeaCommonDTO;
    }

    public void setWorkFmeaCommonDTO(WorkFmeaCommonDTO workFmeaCommonDTO)
    {
        this.workFmeaCommonDTO = workFmeaCommonDTO;
    }

    public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO()
    {
        return maWoResultMstrCommonDTO;
    }

    public void setMaWoResultMstrCommonDTO(
            MaWoResultMstrCommonDTO maWoResultMstrCommonDTO)
    {
        this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
    }

    public PartAdjStkTakeCommonDTO getPartAdjStkTakeCommonDTO()
    {
        return partAdjStkTakeCommonDTO;
    }

    public void setPartAdjStkTakeCommonDTO(
            PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO)
    {
        this.partAdjStkTakeCommonDTO = partAdjStkTakeCommonDTO;
    }

    public MaPtBuyReqHdrCommonDTO getMaPtBuyReqHdrCommonDTO()
    {
        return maPtBuyReqHdrCommonDTO;
    }

    public void setMaPtBuyReqHdrCommonDTO(
            MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO)
    {
        this.maPtBuyReqHdrCommonDTO = maPtBuyReqHdrCommonDTO;
    }

    public ReqWorkCommonDTO getReqWorkCommonDTO()
    {
        return reqWorkCommonDTO;
    }

    public void setReqWorkCommonDTO(ReqWorkCommonDTO reqWorkCommonDTO)
    {
        this.reqWorkCommonDTO = reqWorkCommonDTO;
    }

    public AppReadyCommonDTO getAppReadyCommonDTO()
    {
        return appReadyCommonDTO;
    }

    public void setAppReadyCommonDTO(AppReadyCommonDTO appReadyCommonDTO)
    {
        this.appReadyCommonDTO = appReadyCommonDTO;
    }

    public MaWoDailyCommonDTO getMaWoDailyCommonDTO()
    {
        return maWoDailyCommonDTO;
    }

    public void setMaWoDailyCommonDTO(MaWoDailyCommonDTO maWoDailyCommonDTO)
    {
        this.maWoDailyCommonDTO = maWoDailyCommonDTO;
    }

    public InvtCommonDTO getInvtCommonDTO()
    {
        return invtCommonDTO;
    }

    public void setInvtCommonDTO(InvtCommonDTO invtCommonDTO)
    {
        this.invtCommonDTO = invtCommonDTO;
    }
    
}
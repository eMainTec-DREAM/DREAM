package dream.pers.appr.form;

import common.struts.BaseForm;
import dream.asset.list.dto.MaEqMstrCommonDTO;
import dream.invt.list.dto.InvtCommonDTO;
import dream.note.daily.dto.MaWoDailyCommonDTO;
import dream.part.adj.stktake.dto.PartAdjStkTakeCommonDTO;
import dream.part.iss.emg.req.dto.PartIssEmgReqCommonDTO;
import dream.part.pur.buy.dto.MaPtBuyReqHdrCommonDTO;
import dream.pers.appr.dto.PersApprHistCommonDTO;
import dream.req.work.dto.ReqWorkCommonDTO;
import dream.work.cal.pminsappr.dto.WorkCalPmInsApprCommonDTO;
import dream.work.fmea.list.dto.WorkFmeaCommonDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.planappr.dto.WorkPlanApprCommonDTO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pmi.list.dto.WorkPmiCommonDTO;
/**
 * 결재이력 - List Form
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 * @struts.form name="persApprHistListForm"
 * */

public class PersApprHistListForm extends BaseForm
{
	
	/** 공통 */
	private PersApprHistCommonDTO persApprHistCommonDTO = new PersApprHistCommonDTO();
	
	/** 일일작업일지확인 */
    private MaWoDailyCommonDTO maWoDailyCommonDTO = new MaWoDailyCommonDTO();
    /** 투자확인 */
    private InvtCommonDTO invtCommonDTO = new InvtCommonDTO();
    /** 작업요청확인 */
    private ReqWorkCommonDTO reqWorkCommonDTO = new ReqWorkCommonDTO();
    /** 구매신청확인 */
    private MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO = new MaPtBuyReqHdrCommonDTO();
    /** 부품실사확인 */
    private PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO = new PartAdjStkTakeCommonDTO();
    /** 작업오더확인 */
    private MaWoResultMstrCommonDTO maWoResultMstrCommonDTO = new MaWoResultMstrCommonDTO();
    /** 고장영향성평가확인 */
    private WorkFmeaCommonDTO workFmeaCommonDTO = new WorkFmeaCommonDTO();
    /** 출고요청확인 */
    private PartIssEmgReqCommonDTO partIssEmgReqCommonDTO = new PartIssEmgReqCommonDTO();
    /** 작업계획확인 */
    private WoPlanCommonDTO woPlanCommonDTO = new WoPlanCommonDTO();
    /** 예방점검계획승인 */
    private WorkCalPmInsApprCommonDTO workCalPmInsApprCommonDTO = new WorkCalPmInsApprCommonDTO();
    /** 작업계획승인 */
    private WorkPlanApprCommonDTO workPlanApprCommonDTO = new WorkPlanApprCommonDTO();
    /** 설비제개정 결재 */
    private MaEqMstrCommonDTO maEqMstrCommonDTO = new MaEqMstrCommonDTO();
    /** 점검실시 결재 */
    private WorkPmiCommonDTO workPmiCommonDTO = new WorkPmiCommonDTO();
    /** 점검주기설정 결재 */
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
	public PersApprHistCommonDTO getPersApprHistCommonDTO() {
		return persApprHistCommonDTO;
	}
	public void setPersApprHistCommonDTO(PersApprHistCommonDTO persApprHistCommonDTO) {
		this.persApprHistCommonDTO = persApprHistCommonDTO;
	}
	public MaWoDailyCommonDTO getMaWoDailyCommonDTO() {
		return maWoDailyCommonDTO;
	}
	public void setMaWoDailyCommonDTO(MaWoDailyCommonDTO maWoDailyCommonDTO) {
		this.maWoDailyCommonDTO = maWoDailyCommonDTO;
	}
	public InvtCommonDTO getInvtCommonDTO() {
		return invtCommonDTO;
	}
	public void setInvtCommonDTO(InvtCommonDTO invtCommonDTO) {
		this.invtCommonDTO = invtCommonDTO;
	}
	public ReqWorkCommonDTO getReqWorkCommonDTO() {
		return reqWorkCommonDTO;
	}
	public void setReqWorkCommonDTO(ReqWorkCommonDTO reqWorkCommonDTO) {
		this.reqWorkCommonDTO = reqWorkCommonDTO;
	}
	public MaPtBuyReqHdrCommonDTO getMaPtBuyReqHdrCommonDTO() {
		return maPtBuyReqHdrCommonDTO;
	}
	public void setMaPtBuyReqHdrCommonDTO(MaPtBuyReqHdrCommonDTO maPtBuyReqHdrCommonDTO) {
		this.maPtBuyReqHdrCommonDTO = maPtBuyReqHdrCommonDTO;
	}
	public PartAdjStkTakeCommonDTO getPartAdjStkTakeCommonDTO() {
		return partAdjStkTakeCommonDTO;
	}
	public void setPartAdjStkTakeCommonDTO(PartAdjStkTakeCommonDTO partAdjStkTakeCommonDTO) {
		this.partAdjStkTakeCommonDTO = partAdjStkTakeCommonDTO;
	}
	public MaWoResultMstrCommonDTO getMaWoResultMstrCommonDTO() {
		return maWoResultMstrCommonDTO;
	}
	public void setMaWoResultMstrCommonDTO(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO) {
		this.maWoResultMstrCommonDTO = maWoResultMstrCommonDTO;
	}
	public WorkFmeaCommonDTO getWorkFmeaCommonDTO() {
		return workFmeaCommonDTO;
	}
	public void setWorkFmeaCommonDTO(WorkFmeaCommonDTO workFmeaCommonDTO) {
		this.workFmeaCommonDTO = workFmeaCommonDTO;
	}
	public PartIssEmgReqCommonDTO getPartIssEmgReqCommonDTO() {
		return partIssEmgReqCommonDTO;
	}
	public void setPartIssEmgReqCommonDTO(PartIssEmgReqCommonDTO partIssEmgReqCommonDTO) {
		this.partIssEmgReqCommonDTO = partIssEmgReqCommonDTO;
	}
	public WoPlanCommonDTO getWoPlanCommonDTO() {
		return woPlanCommonDTO;
	}
	public void setWoPlanCommonDTO(WoPlanCommonDTO woPlanCommonDTO) {
		this.woPlanCommonDTO = woPlanCommonDTO;
	}
	
	
}

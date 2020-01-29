package dream.scheduler.autoschedule.service.spring;

import com.sap.mw.jco.JCO;

import common.bean.User;
import common.util.CommonUtil;
import dream.scheduler.autoschedule.dao.SchAutoSchCommonDAO;
import dream.scheduler.autoschedule.service.MaBatchService;
import dream.scheduler.autoschedule.service.SchAutoSchCommonService;

/**
 * Batch 서비스 구현
 * 
 * @author kim21017
 * @version $Id: Exp $
 * @since 1.0
 * @spring.bean id="schAutoSchCommonServiceTarget"
 * @spring.txbn id="schAutoSchCommonService"
 * @spring.property name="schAutoSchCommonDAO" ref="schAutoSchCommonDAO"
 */
public class SchAutoSchCommonServiceImpl implements SchAutoSchCommonService {

	String[] compNo;
	String userNo = "";
	String email = "";
	
	JCO.Client mConnection;
	JCO.Repository mRepository;
	
	JCO.Function function = null;
	JCO.Table codes = null;

	private SchAutoSchCommonDAO schAutoSchCommonDAO = null;

	public SchAutoSchCommonDAO getSchAutoSchCommonDAO() {
		return schAutoSchCommonDAO;
	}

	public void setSchAutoSchCommonDAO(SchAutoSchCommonDAO schAutoSchCommonDAO) {
		this.schAutoSchCommonDAO = schAutoSchCommonDAO;
	}

	public void SP_PM_MAKE_SCHEDULE_BYALL() throws Exception {
		compNo = schAutoSchCommonDAO.getIfCompNo();
		userNo = schAutoSchCommonDAO.getIfUserNo();
		
		for (int i = 0; i < compNo.length; i++) {
			User user = new User();
			user.setCompNo(compNo[i]);
			
			MaBatchService maBatchService = (MaBatchService)CommonUtil.getBean("maBatchService", user);
			maBatchService.SP_PM_MAKE_SCHEDULE_BYALL(compNo[i], userNo);
		}
	}

	public void SP_PM_MAKE_TO_ALLSCHED() throws Exception {
		compNo = schAutoSchCommonDAO.getIfCompNo();
		userNo = schAutoSchCommonDAO.getIfUserNo();
		for (int i = 0; i < compNo.length; i++) {
			User user = new User();
			user.setCompNo(compNo[i]);
			
			MaBatchService maBatchService = (MaBatchService)CommonUtil.getBean("maBatchService", user);
			maBatchService.SP_PM_MAKE_TO_ALLSCHED(compNo[i], userNo);
		}
	}

	public void SP_MAKE_USE_MONITORING() throws Exception {
		compNo = schAutoSchCommonDAO.getIfCompNo();
		userNo = schAutoSchCommonDAO.getIfUserNo();
		for (int i = 0; i < compNo.length; i++) {
			User user = new User();
			user.setCompNo(compNo[i]);
			
			MaBatchService maBatchService = (MaBatchService)CommonUtil.getBean("maBatchService", user);
			maBatchService.SP_MAKE_USE_MONITORING(compNo[i], userNo);
		}
	}

	public void SP_PM_MAKE_TAMTPOINT() throws Exception {
		compNo = schAutoSchCommonDAO.getIfCompNo();
		userNo = schAutoSchCommonDAO.getIfUserNo();
		for (int i = 0; i < compNo.length; i++) {
			User user = new User();
			user.setCompNo(compNo[i]);
			
			MaBatchService maBatchService = (MaBatchService)CommonUtil.getBean("maBatchService", user);
			maBatchService.SP_PM_MAKE_TAMTPOINT(compNo[i], userNo);
		}
	}

	public void SP_MAKE_TAINVESTAMT() throws Exception {
		compNo = schAutoSchCommonDAO.getIfCompNo();
		userNo = schAutoSchCommonDAO.getIfUserNo();
		for (int i = 0; i < compNo.length; i++) {
			User user = new User();
			user.setCompNo(compNo[i]);
			
			MaBatchService maBatchService = (MaBatchService)CommonUtil.getBean("maBatchService", user);
			maBatchService.SP_MAKE_TAINVESTAMT(compNo[i], userNo);
		}
	}

	public void SP_MAKE_TALNWRKTIME() throws Exception {
		compNo = schAutoSchCommonDAO.getIfCompNo();
		userNo = schAutoSchCommonDAO.getIfUserNo();
		for (int i = 0; i < compNo.length; i++) {
			User user = new User();
			user.setCompNo(compNo[i]);
			
			MaBatchService maBatchService = (MaBatchService)CommonUtil.getBean("maBatchService", user);
			maBatchService.SP_MAKE_TALNWRKTIME(compNo[i], userNo);
		}
	}

	public void SP_SETDEFAULT_DUMYDAYS() throws Exception {
		compNo = schAutoSchCommonDAO.getIfCompNo();
		userNo = schAutoSchCommonDAO.getIfUserNo();
		for (int i = 0; i < compNo.length; i++) {
			User user = new User();
			user.setCompNo(compNo[i]);
			
			MaBatchService maBatchService = (MaBatchService)CommonUtil.getBean("maBatchService", user);
			maBatchService.SP_SETDEFAULT_DUMYDAYS(compNo[i], userNo);
		}
	}

	public void SP_KPI_MAKE_TAKPIDLOCDN() throws Exception {
		compNo = schAutoSchCommonDAO.getIfCompNo();
		userNo = schAutoSchCommonDAO.getIfUserNo();
		for (int i = 0; i < compNo.length; i++) {
			User user = new User();
			user.setCompNo(compNo[i]);
			
			MaBatchService maBatchService = (MaBatchService)CommonUtil.getBean("maBatchService", user);
			maBatchService.SP_KPI_MAKE_TAKPIDLOCDN(compNo[i], userNo);
		}
	}

	public void SP_KPI_MAKE_TAKPIMLOCDN() throws Exception {
		compNo = schAutoSchCommonDAO.getIfCompNo();
		userNo = schAutoSchCommonDAO.getIfUserNo();
		for (int i = 0; i < compNo.length; i++) {
			User user = new User();
			user.setCompNo(compNo[i]);
			
			MaBatchService maBatchService = (MaBatchService)CommonUtil.getBean("maBatchService", user);
			maBatchService.SP_KPI_MAKE_TAKPIMLOCDN(compNo[i], userNo);
		}
	}

	public void SP_KPI_MAKE_TAKPIDLOCCTGDN() throws Exception {
		compNo = schAutoSchCommonDAO.getIfCompNo();
		userNo = schAutoSchCommonDAO.getIfUserNo();
		for (int i = 0; i < compNo.length; i++) {
			User user = new User();
			user.setCompNo(compNo[i]);
			
			MaBatchService maBatchService = (MaBatchService)CommonUtil.getBean("maBatchService", user);
			maBatchService.SP_KPI_MAKE_TAKPIDLOCCTGDN(compNo[i], userNo);
		}
	}

	public void SP_KPI_MAKE_TAKPIMLOCCTGDN() throws Exception {
		compNo = schAutoSchCommonDAO.getIfCompNo();
		userNo = schAutoSchCommonDAO.getIfUserNo();
		for (int i = 0; i < compNo.length; i++) {
			User user = new User();
			user.setCompNo(compNo[i]);
			
			MaBatchService maBatchService = (MaBatchService)CommonUtil.getBean("maBatchService", user);
			maBatchService.SP_KPI_MAKE_TAKPIMLOCCTGDN(compNo[i], userNo);
		}
	}

	public void SP_KPI_MAKE_TAKPIMMPOINT() throws Exception {
		compNo = schAutoSchCommonDAO.getIfCompNo();
		userNo = schAutoSchCommonDAO.getIfUserNo();
		for (int i = 0; i < compNo.length; i++) {
			User user = new User();
			user.setCompNo(compNo[i]);
			
			MaBatchService maBatchService = (MaBatchService)CommonUtil.getBean("maBatchService", user);
			maBatchService.SP_KPI_MAKE_TAKPIMMPOINT(compNo[i], userNo);
		}
	}

	public void SP_KPI_MAKE_TAKPIWMPOINT() throws Exception {
		compNo = schAutoSchCommonDAO.getIfCompNo();
		userNo = schAutoSchCommonDAO.getIfUserNo();
		for (int i = 0; i < compNo.length; i++) {
			User user = new User();
			user.setCompNo(compNo[i]);
			
			MaBatchService maBatchService = (MaBatchService)CommonUtil.getBean("maBatchService", user);
			maBatchService.SP_KPI_MAKE_TAKPIWMPOINT(compNo[i], userNo);
		}
	}

	public void SP_KPI_MAKE_TAKPIMEDU() throws Exception {
		compNo = schAutoSchCommonDAO.getIfCompNo();
		userNo = schAutoSchCommonDAO.getIfUserNo();
		for (int i = 0; i < compNo.length; i++) {
			User user = new User();
			user.setCompNo(compNo[i]);
			
			MaBatchService maBatchService = (MaBatchService)CommonUtil.getBean("maBatchService", user);
			maBatchService.SP_KPI_MAKE_TAKPIMEDU(compNo[i], userNo);
		}
	}

	public void SP_KPI_MAKE_TAPTMONTHLYSTOCK() throws Exception {
		compNo = schAutoSchCommonDAO.getIfCompNo();
		userNo = schAutoSchCommonDAO.getIfUserNo();
		for (int i = 0; i < compNo.length; i++) {
			User user = new User();
			user.setCompNo(compNo[i]);
			
			MaBatchService maBatchService = (MaBatchService)CommonUtil.getBean("maBatchService", user);
			maBatchService.SP_KPI_MAKE_TAPTMONTHLYSTOCK(compNo[i], userNo);
		}
	}

	public void SP_WOMAKE_4WP_BYALL() throws Exception {
		compNo = schAutoSchCommonDAO.getIfCompNo();
		userNo = schAutoSchCommonDAO.getIfUserNo();
		for (int i = 0; i < compNo.length; i++) {
			User user = new User();
			user.setCompNo(compNo[i]);
			
			MaBatchService maBatchService = (MaBatchService)CommonUtil.getBean("maBatchService", user);
			maBatchService.SP_WOMAKE_4WP_BYALL(compNo[i], userNo);
		}
	}

	public void COMP_INTERFACE() throws Exception {
		compNo = schAutoSchCommonDAO.getIfCompNo();
		userNo = schAutoSchCommonDAO.getIfUserNo();

		for (int i = 0; i < compNo.length; i++) {
			User user = new User();
			user.setCompNo(compNo[i]);
			
			MaBatchService maBatchService = (MaBatchService)CommonUtil.getBean("maBatchService", user);
			maBatchService.COMP_INTERFACE(compNo[i], userNo);
		}
	}
	public void SP_IF_UPD_CTCTR() throws Exception {
		compNo = schAutoSchCommonDAO.getIfCompNo();
		userNo = schAutoSchCommonDAO.getIfUserNo();

		for (int i = 0; i < compNo.length; i++) {
			User user = new User();
			user.setCompNo(compNo[i]);
			
			MaBatchService maBatchService = (MaBatchService)CommonUtil.getBean("maBatchService", user);
			//CP
			maBatchService.SP_IF_UPD_CTCTR(compNo[i],userNo);
		}
	}
	public void SP_IF_UPD_DEPT() throws Exception {
		compNo = schAutoSchCommonDAO.getIfCompNo();
		userNo = schAutoSchCommonDAO.getIfUserNo();

		for (int i = 0; i < compNo.length; i++) {
			User user = new User();
			user.setCompNo(compNo[i]);
			
			MaBatchService maBatchService = (MaBatchService)CommonUtil.getBean("maBatchService", user);
			//부서
			maBatchService.SP_IF_UPD_DEPT(compNo[i],userNo);
		}
	}
	public void SP_IF_UPD_EMP() throws Exception {
		compNo = schAutoSchCommonDAO.getIfCompNo();
		userNo = schAutoSchCommonDAO.getIfUserNo();

		for (int i = 0; i < compNo.length; i++) {
			User user = new User();
			user.setCompNo(compNo[i]);
			
			MaBatchService maBatchService = (MaBatchService)CommonUtil.getBean("maBatchService", user);
			//사원
			maBatchService.SP_IF_UPD_EMP(compNo[i],userNo);
		}
	}
	public void SP_IF_UPD_VENDOR() throws Exception {
		compNo = schAutoSchCommonDAO.getIfCompNo();
		userNo = schAutoSchCommonDAO.getIfUserNo();
		
		for (int i = 0; i < compNo.length; i++) {
			User user = new User();
			user.setCompNo(compNo[i]);
			
			MaBatchService maBatchService = (MaBatchService)CommonUtil.getBean("maBatchService", user);
			//거래처
			maBatchService.SP_IF_UPD_VENDOR(compNo[i],userNo);
		}
	}

	@Override
	public void sendMailMessageList() throws Exception {
		compNo = schAutoSchCommonDAO.getIfCompNo();
		userNo = schAutoSchCommonDAO.getIfUserNo();
		
		for (int i = 0; i < compNo.length; i++) {
			User user = new User();
			user.setCompNo(compNo[i]);
			
			MaBatchService maBatchService = (MaBatchService)CommonUtil.getBean("maBatchService", user);
			maBatchService.sendMailMessageList(compNo[i],userNo);
		}
	}
	
}

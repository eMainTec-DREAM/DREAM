package dream.scheduler.autoschedule.service;
/**
 * Batch ���� �������̽�
 * @author kim21017
 *
 */
public interface SchAutoSchCommonService
{
	/**
	 * �����۾� ��ü������ �����ϴ� ���α׷�
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	void SP_PM_MAKE_SCHEDULE_BYALL() throws Exception;
	/**
	 * �����۾� �������� �۾���(WO)�� �ڵ����� ������ �ִ� ���α׷�
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	void SP_PM_MAKE_TO_ALLSCHED() throws Exception;
	/**
	 * �Ϻ��� �����Ȳ����͸� �����͸� �������ִ� ���α׷�
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	void SP_MAKE_USE_MONITORING() throws Exception;
	/**
	 * default ������ǥ�� �������ִ� ���α׷�.
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	void SP_PM_MAKE_TAMTPOINT() throws Exception;
	/**
	 * default ���ڱݾ��� �������ִ� ���α׷�.
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	void SP_MAKE_TAINVESTAMT() throws Exception;
	/**
	 * default ���ΰ����ð� �������ִ� ���α׷�.
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	void SP_MAKE_TALNWRKTIME() throws Exception;
	/**
	 * ���� ����ī���ٸ� default�� ������ִ� ���α׷�
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	void SP_SETDEFAULT_DUMYDAYS() throws Exception;
	/**
	 * �Ϻ������� �����͸� �����ϴ� ���α׷�[���� 1�Ϻ��� ���ñ��� �����]
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	void SP_KPI_MAKE_TAKPIDLOCDN() throws Exception;
	/**
	 * ���������� �����͸� �����ϴ� ���α׷�[����,��� �����]
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	void SP_KPI_MAKE_TAKPIMLOCDN() throws Exception;
	/**
	 * ���������������� �����͸� �����ϴ� ���α׷�[���� 1�Ϻ��� ���ñ��� �����]
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	void SP_KPI_MAKE_TAKPIDLOCCTGDN() throws Exception;
	/**
	 * ���������� ������ �����͸� ������ ����
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	void SP_KPI_MAKE_TAKPIMLOCCTGDN() throws Exception;
	/**
	 * ���������׸� ��ǥ �����͸� �����ϴ� ���α׷�.
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	void SP_KPI_MAKE_TAKPIMMPOINT() throws Exception;
	/**
	 * �ְ����� ������ǥ �����͸� �����ϴ� ���α׷�
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	void SP_KPI_MAKE_TAKPIWMPOINT() throws Exception;
	/**
	 * ����������ȹ ��ǥ �����͸� �����ϴ� ���α׷�
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	void SP_KPI_MAKE_TAKPIMEDU() throws Exception;
	/**
	 * ���� ���Һθ� �����ϴ� ���α׷�
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	void SP_KPI_MAKE_TAPTMONTHLYSTOCK() throws Exception;
	
	
	void SP_WOMAKE_4WP_BYALL() throws Exception;
	/**
	 * ���庰 ���ν��� �������̽�
	 * @throws Exception
	 */
	void COMP_INTERFACE() throws Exception;
	/**
	 * CP �������̽�
	 * @throws Exception
	 */
	void SP_IF_UPD_CTCTR() throws Exception;
	/**
	 * �μ� �������̽�
	 * @throws Exception
	 */
	void SP_IF_UPD_DEPT() throws Exception;
	/**
	 * ��� �������̽�
	 * @throws Exception
	 */
	void SP_IF_UPD_EMP() throws Exception;
	/**
	 * �ŷ�ó �������̽�
	 * @throws Exception
	 */
	void SP_IF_UPD_VENDOR() throws Exception;
	
	/**
	 * ���ϼ��� ����
	 * @throws Exception
	 */
	public void sendMailMessageList() throws Exception;
}

package dream.consult.program.table.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.table.form.TableColValLovForm;
/**
 * Å×ÀÌºí Column LOV - List Service
 * @author kim21017O
 * @version $Id: TableColValLovService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface TableColValLovService {
	/**
	 * FIND LIST
	 * @param tableColValLovForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findList(TableColValLovForm tableColValLovForm, User user) throws Exception;
	/**
	 * FIND LIST COUNT
	 * @param tableColValLovForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String findTotalCount(TableColValLovForm tableColValLovForm, User user) throws Exception;
}

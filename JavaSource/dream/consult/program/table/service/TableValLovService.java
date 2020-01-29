package dream.consult.program.table.service;

import java.util.List;

import common.bean.User;
import dream.consult.program.table.form.TableValLovForm;
/**
 * Å×ÀÌºí LOV - List Service
 * @author kim21017
 * @version $Id: TableValLovService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface TableValLovService {
	/**
	 * FIND LIST
	 * @param tableValLovForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findList(TableValLovForm tableValLovForm, User user) throws Exception;
	/**
	 * FIND LIST COUNT
	 * @param tableValLovForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public String findTotalCount(TableValLovForm tableValLovForm, User user) throws Exception;
}

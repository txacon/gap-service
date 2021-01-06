package com.txacon.gap.domain.report.port;

import com.txacon.gap.domain.bussines.entities.Business;
import net.sf.jasperreports.engine.JasperPrint;

public interface MenuReport {

    JasperPrint createPdfReport(Business businessId);

}

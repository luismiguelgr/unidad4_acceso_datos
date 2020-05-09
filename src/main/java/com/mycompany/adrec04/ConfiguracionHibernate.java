/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.adrec04;

/**
 *
 * @author miguel
 */

    public class ConfiguracionHibernate {
        private String driver;
        private String dialect;
        private String HBM2DDL_AUTO;
        private boolean SHOW_SQL;

        public ConfiguracionHibernate(){
            
        }
        
        public ConfiguracionHibernate(String driver, String dialect, String HBM2DDL_AUTO, boolean SHOW_SQL){
            this.driver = driver;
            this.dialect = dialect;
            this.HBM2DDL_AUTO = HBM2DDL_AUTO;
            this.SHOW_SQL = SHOW_SQL;
        }

        public String getDriver() {
            return driver;
        }

        public void setDriver(String driver) {
            this.driver = driver;
        }

        public String getDialect() {
            return dialect;
        }

        public void setDialect(String dialect) {
            this.dialect = dialect;
        }

        public String getHBM2DDL_AUTO() {
            return HBM2DDL_AUTO;
        }

        public void setHBM2DDL_AUTO(String HBM2DDL_AUTO) {
            this.HBM2DDL_AUTO = HBM2DDL_AUTO;
        }

        public boolean isSHOW_SQL() {
            return SHOW_SQL;
        }

        public void setSHOW_SQL(boolean SHOW_SQL) {
            this.SHOW_SQL = SHOW_SQL;
        }


        public String toString() {
            return "Driver=" + driver + ", Dialect=" + dialect + ", HBM2DDL_AUTO=" + HBM2DDL_AUTO + ", SHOW_SQL=" + SHOW_SQL;
        }
    }

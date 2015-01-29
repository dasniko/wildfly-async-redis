package de.nk.redis.rest;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import org.jboss.resteasy.plugins.server.servlet.HttpServlet30Dispatcher;

@WebServlet(asyncSupported = true, urlPatterns = "/*", initParams = {
        @WebInitParam(name = "resteasy.scan", value = "true"),
        @WebInitParam(name = "resteasy.servlet.mapping.prefix", value = "/") })
public class ResteasyDispatcher extends HttpServlet30Dispatcher {
    private static final long serialVersionUID = 1L;
}

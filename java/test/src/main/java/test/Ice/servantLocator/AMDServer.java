// **********************************************************************
//
// Copyright (c) 2003-2016 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

package test.Ice.servantLocator;

public class AMDServer extends test.Util.Application
{
    @Override
    public int run(String[] args)
    {

        com.zeroc.Ice.ObjectAdapter adapter = communicator().createObjectAdapter("TestAdapter");
        adapter.addServantLocator(new AMDServantLocatorI("category"), "category");
        adapter.addServantLocator(new AMDServantLocatorI(""), "");
        adapter.add(new AMDTestI(), com.zeroc.Ice.Util.stringToIdentity("asm"));
        adapter.add(new AMDTestActivationI(), com.zeroc.Ice.Util.stringToIdentity("test/activation"));
        adapter.activate();
        return WAIT;
    }

    @Override
    protected GetInitDataResult getInitData(String[] args)
    {
        GetInitDataResult r = super.getInitData(args);
        r.initData.properties.setProperty("Ice.Package.Test", "test.Ice.servantLocator.AMD");
        r.initData.properties.setProperty("TestAdapter.Endpoints", getTestEndpoint(r.initData.properties, 0));
        r.initData.properties.setProperty("Ice.Warn.Dispatch", "0");

        return r;
    }

    public static void main(String[] args)
    {
        AMDServer app = new AMDServer();
        int result = app.main("AMDServer", args);
        System.gc();
        System.exit(result);
    }
}

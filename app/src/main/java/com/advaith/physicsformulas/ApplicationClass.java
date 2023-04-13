package com.advaith.physicsformulas;

import android.app.Application;

import java.util.ArrayList;

public class ApplicationClass extends Application {

    public static int subjectIndex = -1;
    public static int formulaIndex = -1;

    public static int physicsSubjectIndex = -1;
    public static int physicsFormulaIndex = -1;

    public static int calculusSubjectIndex = -1;
    public static int calculusFormulaIndex = -1;

    public static ArrayList<Course> courses; //main arraylist

    public static ArrayList<Subject> physicsArray;
    public static ArrayList<Subject> calculusArray;

    public static ArrayList<Subject> subjects; //temporary for initializing courses

    public static ArrayList<Moi> mois; //Arraylist of Moment of Inertias

    public static ArrayList<Formula> commonLimits;
    public static ArrayList<Formula> commonDerivs;
    public static ArrayList<Formula> commonIntegrals;

    //all courses declared here

    public static Course physics;
    public static Course calculus;

    public static boolean firstTime = true;

    final int kinematics_img1layout = R.layout.physics_kinematics_1;
    final int kinematics_img2layout = R.layout.physics_kinematics_2;
    final int kinematics_img3layout = R.layout.physics_kinematics_3;
    final int kinematics_img4layout = R.layout.physics_kinematics_4;

    final int dynamics_img1layout = R.layout.physics_dynamics_1;
    final int dynamics_img2layout = R.layout.physics_dynamics_2;
    final int dynamics_img3layout = R.layout.physics_dynamics_3;
    final int dynamics_img4layout = R.layout.physics_dynamics_4;
    final int dynamics_img5layout = R.layout.physics_dynamics_5;

    final int centri_img1layout = R.layout.physics_circular_1;
    final int centri_img2layout = R.layout.physics_circular_2;
    final int centri_img3layout = R.layout.physics_circular_3;
    final int centri_img4layout = R.layout.physics_circular_4;
    final int centri_img5layout = R.layout.physics_circular_5;
    final int centri_img6layout = R.layout.physics_circular_6;

    final int grav_img1layout = R.layout.physics_grav_1;
    final int grav_img2layout = R.layout.physics_grav_2;

    final int energy_img1layout = R.layout.physics_energy_1;
    final int energy_img2layout = R.layout.physics_energy_2;
    final int energy_img3layout = R.layout.physics_energy_3;
    final int energy_img4layout = R.layout.physics_energy_4;
    final int energy_img5layout = R.layout.physics_energy_5;
    final int energy_img6layout = R.layout.physics_energy_6;
    final int energy_img7layout = R.layout.physics_energy_7;
    final int energy_img8layout = R.layout.physics_energy_8;

    final int moment_img1layout = R.layout.physics_momentum_1;
    final int moment_img2layout = R.layout.physics_momentum_2;
    final int moment_img3layout = R.layout.physics_momentum_3;
    final int moment_img4layout = R.layout.physics_momentum_4;

    final int shm_img1layout = R.layout.physics_shm_1;
    final int shm_img2layout = R.layout.physics_shm_2;
    final int shm_img3layout = R.layout.physics_shm_3;
    final int shm_img4layout = R.layout.physics_shm_4;
    final int shm_img5layout = R.layout.physics_shm_5;
    final int shm_img6layout = R.layout.physics_shm_6;

    final int rot_img1layout = R.layout.physics_rot_1;
    final int rot_img2layout = R.layout.physics_rot_2;
    final int rot_img3layout = R.layout.physics_rot_3;
    final int rot_img4layout = R.layout.physics_rot_4;

    final int coming_soon = R.drawable.coming_soon;

    //Calculus Layouts
    final int limits_1layout = R.layout.calculus_limits_1;
    final int limits_2layout = R.layout.calculus_limits_2;
    final int limits_3layout = R.layout.calculus_limits_3;
    final int limits_4layout = R.layout.calculus_limits_4;
    final int limits_5layout = R.layout.calculus_limits_5;
    final int limits_6layout = R.layout.calculus_limits_6;
    final int limits_7layout = R.layout.calculus_limits_7;

    final int derivs_1layout = R.layout.calculus_derivs_1;
    final int derivs_2layout = R.layout.calculus_derivs_2;
    final int derivs_3layout = R.layout.calculus_derivs_3;
    final int derivs_4layout = R.layout.calculus_derivs_4;
    final int derivs_5layout = R.layout.calculus_derivs_5;
    final int derivs_6layout = R.layout.calculus_derivs_6;
    final int derivs_7layout = R.layout.calculus_derivs_7;
    final int derivs_8layout = R.layout.calculus_derivs_8;

    final int ints_1layout = R.layout.calculus_ints_1;
    final int ints_2layout = R.layout.calculus_ints_2;
    final int ints_3layout = R.layout.calculus_ints_3;
    final int ints_4layout = R.layout.calculus_ints_4;
    final int ints_5layout = R.layout.calculus_ints_5;
    final int ints_6layout = R.layout.calculus_ints_6;
    final int ints_7layout = R.layout.calculus_ints_7;

    final int moi1 = R.drawable.moi_1;
    final int moi2 = R.drawable.moi_2;
    final int moi3 = R.drawable.moi_3;
    final int moi4 = R.drawable.moi_4;
    final int moi5 = R.drawable.moi_5;
    final int moi6 = R.drawable.moi_6;
    final int moi7 = R.drawable.moi_7;

    @Override
    public void onCreate() {
        super.onCreate();

        //BEGIN PHYSICS COURSE
        ArrayList<Formula> kinematics = new ArrayList<>();
        kinematics.add(new Formula("v=v0+at",
                "$$v=v_0+at$$"
        , 110, kinematics_img1layout));//v=v0+at
        kinematics.add(new Formula("Δx=v0t+1/2at^2",
                "$$Δx=v_0t+1/2at^2$$"
        , 110, kinematics_img2layout));//Δx = vt+1/2at^2
        kinematics.add(new Formula("Δx=t*(v+v0)/2",
                "$$Δx=t{v+v_0}/2$$"
        , 110, kinematics_img3layout));//deltax = t((v+v₀)/2)
        kinematics.add(new Formula("v^2=v0^2+2aΔx",
                "$$v^2=v_0^2+2aΔx$$"
        , 110, kinematics_img4layout));//v^2=v₀^2+2aΔx

        ArrayList<Formula> dynamics = new ArrayList<>();
        dynamics.add(new Formula("F=ma",
                "$$F_{net}=ma$$"
        , 110, dynamics_img1layout));//f=ma
        dynamics.add(new Formula("W=mg",
                "$$W=mg$$"
        , 110, dynamics_img2layout));//W=mg
        dynamics.add(new Formula("Ff=μk",
                "$$F_f=μk$$"
        , 110, dynamics_img3layout));//Ff=mu*k
        dynamics.add(new Formula("P=mv",
                "$$P=mv$$"
        , 110, dynamics_img4layout));//P=mv
        dynamics.add(new Formula("F=-kx",
                "$$F=-kx$$"
        , 110, dynamics_img5layout));//F=-kx



        ArrayList<Formula> circular = new ArrayList<>();
        circular.add(new Formula("Fc=m*(v^2)/r=mω^2r",
                "$$F_c=m{v^2}/r=mω^2r$$"
        , 110, centri_img1layout));//mv^2/r
        circular.add(new Formula("ω=ω0+αt",
                "$$ω=ω_0+αt$$"//Symbols: 𝛂, θ, ₀, ω, Δ
        , 110, centri_img2layout));//Omega - omeganot+at
        circular.add(new Formula("Δθ=ω0t+1/2αt^2",
                "$$Δθ=ω_0t+1/2αt^2$$"
        , 110, centri_img3layout));//θ = ω₀t + 1/2𝛂t^2
        circular.add(new Formula("ω^2=ω0^2+2αΔθ",
                "$$ω^2=ω_0^2+2αΔθ$$"
        , 110, centri_img4layout));//w^2=w0^2+2atheta
        circular.add(new Formula("v=ωr",
                "$$v=ωr$$"
        , 110, centri_img5layout));//v=wr
        circular.add(new Formula("a=αr",
                "$$a=αr$$"
        , 110, centri_img6layout));//a=ar



        ArrayList<Formula> gravitation = new ArrayList<>();
        gravitation.add(new Formula("FG=(Gm1m2)/r^2",
                "$$F_G={Gm_1m_2}/r^2$$"
        , 100, grav_img1layout));//gm1m2/r^2
        gravitation.add(new Formula("g=(GM)/r^2",
                "$$g={GM}/r^2$$"
        , 110, grav_img2layout));//Gm/r^2

        ArrayList<Formula> energy = new ArrayList<>();
        energy.add(new Formula("W=ΔE=FΔx",
                "$$W=ΔE=FΔx$$"
        , 110, energy_img1layout));//W=Fx
        energy.add(new Formula("W=ΔE=τΔθ",
                "$$W_{rot}=ΔE_{rot}=τΔθ$$"
        , 110, energy_img2layout));//W=𝜏*theta
        energy.add(new Formula("P=W/t=Fv",
                "$$P=W/t=Fv$$"
        , 110, energy_img3layout));//P=Fv
        energy.add(new Formula("P=τω",
                "$$P=τω$$"
        , 110, energy_img4layout));//P=tw
        energy.add(new Formula("KE=1/2mv^2",
                "$$KE={1/2}mv^2$$"
        , 110, energy_img5layout));//KE
        energy.add(new Formula("E=1/2Iω^2",
                "$$E_{rot}={1/2}Iω^2$$"
        , 110, energy_img6layout));//1/2Iw^2
        energy.add(new Formula("GPE=mgh",
                "$$GPE=mgh$$"
        , 110, energy_img7layout));//mgh
        energy.add(new Formula("GPE=-Gm1m2/r",
                "$$GPE=-{Gm_1m_2}/r$$"
        , 110, energy_img8layout));//GPE complicated

        ArrayList<Formula> momentum = new ArrayList<>();
        momentum.add(new Formula("P=mv",
                "$$P=mv$$"
        , 110, moment_img1layout));//P=mv
        momentum.add(new Formula("L=Iω",
                "$$L=Iω$$"
        , 110, moment_img2layout));//L=Iw
        momentum.add(new Formula("J=Ft=ΔP",
                "$$J=Ft=ΔP$$"
        , 110, moment_img3layout));//J=Ft
        momentum.add(new Formula("J=τt",
                "$$J=τt$$"
        , 110, moment_img4layout));//J=torque*t

        ArrayList<Formula> shm = new ArrayList<>();
        shm.add(new Formula("T=2π√{m/k}",
                "$$T=2π√{m/k}$$"
        , 100, shm_img1layout));//
        shm.add(new Formula("T=2πsqrt(l/g)",
                "$$T=2π√{l/g}$$"
        , 100, shm_img2layout));//
        shm.add(new Formula("T=1/f",
                "$$T=1/f$$"
        , 110, shm_img3layout));//
        shm.add(new Formula("ω=2πf",
                "$$ω=2πf$$"
        , 110, shm_img4layout));//
        shm.add(new Formula("x=Acos(ωt+Φ)",
                "$$x=A\\cos(ωt+Φ)$$"
        , 110, shm_img5layout));//
        shm.add(new Formula("v=-ωAsin(ωt+Φ)",
                "$$v=-ωA\\sin(ωt+Φ)$$"
        , 100, shm_img6layout));//

        ArrayList<Formula> rot = new ArrayList<>();
        rot.add(new Formula("τ=Iα=rF",
                "$$τ=Iα=rF_{tan}$$"
        , 110, rot_img1layout));//t=Ia=Rftan
        rot.add(new Formula("I(PointMass)=mr^2",
                "$$I_{point \\\\  mass}=mr^2$$"
        , 110, rot_img2layout));//I=mr^2
        rot.add(new Formula("I(General)=cmr^2",
                "$$I_{general}=cmr^2$$"
        , 110, rot_img3layout));//I=cmr^2
        rot.add(new Formula("I=I(cm)+md^2",
                "$$I=I_{cm}+md^2$$"
        , 110, rot_img4layout));//Parallel axis theorem


        //ArrayList<Formula> elect = new ArrayList<>();
        //elect.add(new Formula(coming_soon, "Coming Soon!", 0));



        subjects = new ArrayList<>();
        subjects.add(new Subject(kinematics, "Kinematics    "));
        subjects.add(new Subject(dynamics, "Dynamics"));
        subjects.add(new Subject(circular, "Circular Motion"));
        subjects.add(new Subject(gravitation, "Gravitation"));
        subjects.add(new Subject(energy, "Work & Energy"));
        subjects.add(new Subject(momentum, "Momentum"));
        subjects.add(new Subject(shm, "Simple Harmonic Motion"));
        subjects.add(new Subject(rot, "Rotational Motion"));

        physicsArray = new ArrayList<>();
        physicsArray.addAll(subjects);//copies subjects to physicsArray
        physics = new Course(physicsArray, "Physics");
        subjects.clear();

        ArrayList<Formula> limits = new ArrayList<>();
        limits.add(new Formula("Limit Constant Rule",
                "$$\\\\lim↙{x→a}(k·\\\\ f(x))=k·\\\\lim↙{x→a}f(x)$$"
                , 90, limits_1layout));
        limits.add(new Formula("Limit sum rule",
                "$$\\\\lim↙{x→a}(f(x)±g(x))=\\\\lim↙{x→a}f(x)±\\\\lim↙{x→a}g(x)$$"
                , 80, limits_2layout));
        limits.add(new Formula("Limit Multiplication Rule",
                "$$\\\\lim↙{x→a}(f(x)·g(x))=\\\\lim↙{x→a}f(x)·\\\\lim↙{x→a}g(x)$$"
                , 80, limits_3layout));
        limits.add(new Formula("Limit division rule",
                "$$\\\\lim↙{x→a}({f(x)}/{g(x)})={\\\\lim↙{x→a}f(x)}/{\\\\lim↙{x→a}g(x)}$$"
                , 70, limits_4layout));
        limits.add(new Formula("Limit power rule",
                "$$\\\\lim↙{x→a}(f(x))^n=(\\\\lim↙{x→a}f(x))^n$$"
                , 90, limits_5layout));
        limits.add(new Formula("L'hopital's rule",
                "$$\\\\lim↙{x→a}({f(x)}/{g(x)})=\\\\lim↙{x→a}({f\\'(x)}/{g\\'(x)})$$"
                , 90, limits_6layout));
        limits.add(new Formula("Squeeze Theorem",
                "Squeeze Theorem"
                , 90, limits_7layout));
//        limits.add(new Formula(
//                "Intermediate Value Theorem"
//                , 90, kinematics_img1layout));

        ArrayList<Formula> derivs = new ArrayList<>();
        derivs.add(new Formula("Definition of derivative",
                "$$d/{dx}f(x)=\\\\lim↙{h→0}{f(x+h)-f(x)}/h$$"
                , 90, derivs_1layout));
        derivs.add(new Formula("Power rule",
                "$$d/{dx}x^n=nx^{n-1}$$"
                , 90, derivs_2layout));
        derivs.add(new Formula("Constant rule",
                "$$d/{dx}k·f(x)=k·d/{dx}f(x)$$"
                , 90, derivs_3layout));
        derivs.add(new Formula("Sum/Diff rule",
                "$$d/{dx}(f(x)±g(x))=d/{dx}f(x)±d/{dx}g(x)$$"
                , 80, derivs_4layout));
        derivs.add(new Formula("Product Rule",
                "$$d/{dx}(f(x)g(x))=f\\'(x)g(x)+f(x)g\\'(x)$$"
                , 80, derivs_5layout));
        derivs.add(new Formula("Quotient Rule",
                "$$d/{dx}({f(x)}/{g(x)})={f\\'(x)g(x)-g\\'(x)f(x)}/{(g(x))^2}$$"
                , 80, derivs_6layout));
        derivs.add(new Formula("Particle motion rules",
                "$$x\\'(t)=v(t),\\ v\\'(t)=a(t)$$"
                , 90, derivs_7layout));
        derivs.add(new Formula("Inverse function deriv rule",
                "$$(f^{-1}(x))\\'=1/{f\\'(f^{-1}(x))}$$"
                , 90, derivs_8layout));

        ArrayList<Formula> integrals = new ArrayList<>();
        integrals.add(new Formula("Def of Integral",
                "$$∫↙a↖{b}f(x)dx=\\\\lim↙{n\\ ➙\\ ∞}∑↙{i=1}↖nf(x_i)Δx$$"
                , 80, ints_1layout));
        integrals.add(new Formula("Integral power rule",
                "$$∫x^ndx=x^{n+1}/{n+1}+C$$"
                , 80, ints_2layout));
        integrals.add(new Formula("Integral reverse bounds",
                "$$∫↙b↖{a}f(x)dx=-∫↙a↖{b}f(x)dx$$"
                , 80, ints_3layout));
        integrals.add(new Formula("Int constant rule",
                "$$∫k·f(x)dx=k∫f(x)dx$$"
                , 80, ints_4layout));
        integrals.add(new Formula("Sum/Diff rule",
                "$$∫[f(x)±g(x)]dx=∫f(x)dx±∫g(x)dx$$"
                , 70, ints_5layout));
        integrals.add(new Formula("Int by parts",
                "$$∫f·g\\'dx=f·g-∫f\\'·gdx$$"
                , 80, ints_6layout));
        integrals.add(new Formula("Fund Theorem of Calc",
                "$$d/{dx}∫↙a↖{x}f(t)dt=f(x)$$"
                , 80, ints_7layout));



        subjects.add(new Subject(limits, "Limits"));
        subjects.add(new Subject(derivs, "Derivatives"));
        subjects.add(new Subject(integrals, "Integrals"));

        calculusArray = new ArrayList<>();
        calculusArray.addAll(subjects);
        calculus = new Course(calculusArray, "Calculus");
        subjects.clear();


        courses = new ArrayList<>();
        courses.add(physics);
        courses.add(calculus);

        mois = new ArrayList<>();

        mois.add(new Moi("$$I=1/12mL^2$$", moi1,
                "Thin Rod of length L and mass m with axis through center."
        ));
        mois.add(new Moi("$$I=1/3mL^2$$", moi2,
                "Thin Rod of length L and mass m with axis through one end."
        ));
        mois.add(new Moi("$$I=1/2mR^2$$", moi3,
                "Solid Cylinder or Disk of radius R and mass m with symmetry axis."
        ));
        mois.add(new Moi("$$I=1/4mR^2+1/12mL^2$$", moi4,
                "Solid Cylinder of radius R, length/height L, and mass m with axis about central diameter."
        ));
        mois.add(new Moi("$$I=mR^2$$", moi5,
                "Thin hoop of radius R and mass m with symmetry axis."
        ));
        mois.add(new Moi("$$I=2/5mR^2$$", moi6,
                "Solid Sphere of radius R and mass m with axis through center/diameter."
        ));
        mois.add(new Moi("$$I=2/3mR^2$$", moi7,
                "Thin spherical shell of of radius R and mass m with axis through center/diameter."
        ));


        //Common limits/derivs/integrals
        commonLimits = new ArrayList<>();
        commonLimits.add(new Formula("",
                "$$\\\\lim↙{x→0}{\\\\sinx}/x=1$$"
        ,80,0));
        commonLimits.add(new Formula("",
                "$$\\\\lim↙{x→0}{1-\\\\cosx}/x=0$$"
                ,80,0));
        commonLimits.add(new Formula("",
                "$$\\\\lim↙{x→0}{\\\\arcsin\\\\x}/x=1$$"
                ,80,0));
        commonLimits.add(new Formula("",
                "$$\\\\lim↙{x\\ ➙\\±∞}{(1+1/x)}^x=e$$"
                ,70,0));
        commonLimits.add(new Formula("",
                "$$\\\\lim↙{x→0}{(1+x)}^{1/x}=e$$"
                ,70,0));


        commonDerivs = new ArrayList<>();
        commonDerivs.add(new Formula("",
                "$$d/{dx}\\\\sin(x)=\\\\cosx$$"
                ,71,0));
        commonDerivs.add(new Formula("",
                "$$d/{dx}\\\\cos(x)=-\\\\sinx$$"
                ,71,0));
        commonDerivs.add(new Formula("",
                "$$d/{dx}\\\\tan\\\\x=\\\\sec^2x$$"
                ,71,0));
        commonDerivs.add(new Formula("",
                "$$d/{dx}\\\\cot\\\\x=-\\\\csc^2x$$"
                ,71,0));
        commonDerivs.add(new Formula("",
                "$$d/{dx}\\\\sec\\\\x=\\\\sec\\\\x\\\\tan\\\\x$$"
                ,61,0));
        commonDerivs.add(new Formula("",
                "$$d/{dx}\\\\csc\\\\x=-\\\\csc\\\\x\\\\cot\\\\x$$"
                ,61,0));
        commonDerivs.add(new Formula("",
                "$$d/{dx}\\\\sin^{-1}x=1/{√{1-x^2}}$$"
                ,62,0));
        commonDerivs.add(new Formula("",
                "$$d/{dx}\\\\cos^{-1}x=-1/{√{1-x^2}}$$"
                ,62,0));
        commonDerivs.add(new Formula("",
                "$$d/{dx}\\\\tan^{-1}x=1/{1+x^2}$$"
                ,70,0));
        commonDerivs.add(new Formula("",
                "$$d/{dx}a^x=a^x\\\\ln(a)$$"
                ,74,0));
        commonDerivs.add(new Formula("",
                "$$d/{dx}e^x=e^x$$"
                ,74,0));
        commonDerivs.add(new Formula("",
                "$$d/{dx}\\\\ln\\\\x=1/x$$"
                ,74,0));
        commonDerivs.add(new Formula("",
                "$$d/{dx}\\\\log_ax=1/{x\\\\ln(a)}$$"
                ,71,0));



        commonIntegrals = new ArrayList<>();
        commonIntegrals.add(new Formula("",
                "$$∫\\\\cos(x)dx=\\\\sin(x)+C$$"
                ,76,0));
        commonIntegrals.add(new Formula("",
                "$$∫\\\\sin(x)dx=-\\\\cos(x)+C$$"
                ,76,0));
        commonIntegrals.add(new Formula("",
                "$$∫\\\\sec^2(x)dx=\\\\tan(x)+C$$"
                ,76,0));
        commonIntegrals.add(new Formula("",
                "$$∫\\\\csc^2(x)dx=-\\\\cot\\\\x+C$$"
                ,76,0));
        commonIntegrals.add(new Formula("",
                "$$∫\\\\sec\\\\x\\\\tan\\\\x dx=\\\\sec\\\\x+C$$"
                ,76,0));
        commonIntegrals.add(new Formula("",
                "$$∫\\\\csc\\\\x\\\\cot\\\\x dx=-\\\\csc\\\\x+C$$"
                ,76,0));
        commonIntegrals.add(new Formula("",
                "$$∫x^{-1}dx=\\\\ln(|x|)+C$$"
                ,76,0));
        commonIntegrals.add(new Formula("",
                "$$∫\\\\ln\\\\x\\\\dx=x\\\\ln(x)-x+C$$"
                ,76,0));
        commonIntegrals.add(new Formula("",
                "$$∫e^xdx=e^x+C$$"
                ,76,0));
        commonIntegrals.add(new Formula("",
                "$$∫a^xdx=a^x/{\\\\ln\\\\a}$$"
                ,76,0));
    }

    public static void setSubjects(ArrayList<Subject> s){
        subjects = s;
    }

    public static int getSubjectIndex(){
        return subjectIndex;
    }

    public static int getFormulaIndex(){
        return formulaIndex;
    }

    public static void setSubjectIndex(int index){
        subjectIndex = index;
    }
    public static void setFormulaIndex(int index){
        formulaIndex = index;
    }

    public static void setPhysicsSubjectIndex(int index){
        physicsSubjectIndex=index;
    }
    public static void setPhysicsFormulaIndex(int index){
        physicsFormulaIndex = index;
    }

    public static void setCalculusSubjectIndex(int index){
        calculusSubjectIndex = index;
    }
    public static void setCalculusFormulaIndex(int index){
        calculusFormulaIndex = index;
    }

    public static void setSubject(ArrayList<Subject> a){
        subjects = new ArrayList<>();
        subjects.addAll(a);
    }

}

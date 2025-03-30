package com.zeluciojr.enrollments.assemblers.autofeatures;


import com.cae.autodoc.Autodoc;

import java.io.IOException;

public class AutodocRunner {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Autodoc.run(
                "com.zeluciojr.enrollments.assemblers.use_cases",
                "Enrollments",
                false
        );
    }

}

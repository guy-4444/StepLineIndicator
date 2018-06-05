# StepLineIndicator

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/vlad1m1r990/Lemniscate/blob/master/LICENSE)
[![](https://jitpack.io/v/arbelkilani/BiColored-Progress.svg)](https://jitpack.io/#arbelkilani/Compass-View)
[![API](https://img.shields.io/badge/API-19%2B-green.svg?style=flat)]()

Vertical and horizontal step line indicator.

## Setup
Add it in your root build.gradle at the end of repositories:
```
allprojects {
	repositories {
		maven { url 'https://jitpack.io' }
	}
}
```

Step 2. Add the dependency:

```
dependencies {
  implementation 'com.github.guy-4444:StepLineIndicator:1.01.01'
}
```

###### StepView Constructor:
```java
        TimeLineLayout timeLayout_1 = (TimeLineLayout)findViewById(R.id.timeLayout_1);
        timeLayout_1.setTimeLines(this, TimelineView.LineOrientation.HORIZONTAL, 5, R.color.colorPrimary, android.R.color.darker_gray);
        // or:
        TimeLineLayout timeLayout_2 = (TimeLineLayout)findViewById(R.id.timeLayout_2);
        timeLayout_2.setTimeLines(this, TimelineView.LineOrientation.VERTICAL, 12, Color.argb(123, 0, 0, 255), android.R.color.darker_gray, android.R.color.darker_gray, 3, 20, 10);
        // or:
        TimeLineLayout timeLayout_3 = (TimeLineLayout)findViewById(R.id.timeLayout_3);
        timeLayout_2.setTimeLines(this, TimelineView.LineOrientation.VERTICAL, 12, Color.argb(123, 0, 0, 255), android.R.color.darker_gray, android.R.color.darker_gray, 3, 20, 10);
        // or:
        TimeLineLayout timeLayout_4 = (TimeLineLayout)findViewById(R.id.timeLayout_4);
timeLayout_4.setTimeLines(this, TimelineView.LineOrientation.VERTICAL, 60, Color.argb(123, 0, 0, 255), android.R.color.darker_gray, android.R.color.darker_gray, 2, 17, 0);
       
```

###### StepView Edit ways:
```java
        timeLayout_1.setUnitCompleted(0);
        timeLayout_1.setUnitInactive(1);
        timeLayout_1.setUnitActive(2);
```

## License

    Copyright 2018 Guy Isakov

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

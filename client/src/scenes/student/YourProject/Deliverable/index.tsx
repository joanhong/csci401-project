import * as React from 'react';
import SubmitDeliverable from './SubmitDeliverable';
import SubmittedDeliverable from './SubmittedDeliverables';

class Deliverable extends React.Component {
    render() {
        return (
            <div>
                <SubmitDeliverable/>
                <SubmittedDeliverable/>
            </div>
        );
    }
}

export default Deliverable;